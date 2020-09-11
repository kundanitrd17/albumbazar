package com.albumbazaar.albumbazar.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    @Order(1)
    public static class SuperuserSecurityConfig extends WebSecurityConfigurerAdapter {

        private UserDetailsService superuserDetailsService;

        @Autowired
        protected SuperuserSecurityConfig(@Qualifier("superuserDetailsService") UserDetailsService userDetailsService) {
            this.superuserDetailsService = userDetailsService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(superuserDetailsService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/superuser/**").authorizeRequests().anyRequest().hasRole("SUPERUSER").and().formLogin()
                    .loginPage("/login-super").loginProcessingUrl("/superuser/superlogin")
                    .failureUrl("/login-super?error=true").defaultSuccessUrl("/superuser", true).permitAll().and()
                    .logout().logoutUrl("/superuser/logout-super") // If csrf is enabled then logout must be post
                    .logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID", "XSRF-TOKEN");
            // .and()
            // .csrf()
            // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        }
    }

    @Configuration
    @Order(2)
    public static class UserSecurityConfig extends WebSecurityConfigurerAdapter {

        private UserDetailsService employeeService;

        @Autowired
        protected UserSecurityConfig(@Qualifier("employeeService") UserDetailsService userDetailsService) {
            this.employeeService = userDetailsService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(employeeService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http// .csrf().disable()
                    .antMatcher("/user/**").authorizeRequests().anyRequest().hasRole("USER").and().formLogin()
                    .loginPage("/login-user").loginProcessingUrl("/user/userlogin").failureUrl("/login-user?error=true")
                    .defaultSuccessUrl("/user", true).permitAll().and().logout().logoutUrl("/user/logout-user")
                    .logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID", "XSRF-TOKEN");

        }

    }

    @Configuration
    public static class GuestSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/foo").authenticated().anyRequest().permitAll();
        }
    }

    @Bean
    public static PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
