package com.albumbazaar.albumbazar.configuration;

import com.albumbazaar.albumbazar.model.AvailableRoles;

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

@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    @Order(1)
    public static class SuperuserSecurityConfig extends WebSecurityConfigurerAdapter {

        private UserDetailsService superuserDetailsService;

        @Autowired
        protected SuperuserSecurityConfig(@Qualifier("superuserService") UserDetailsService userDetailsService) {
            this.superuserDetailsService = userDetailsService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(superuserDetailsService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/superuser/**").authorizeRequests().antMatchers("/superuser/js/*.js").permitAll()
                    .antMatchers("/superuser/css/*.css").permitAll() // remove in production
                    .anyRequest().hasAuthority(AvailableRoles.Code.SUPERUSER).and().formLogin()
                    .loginPage("/login-super").loginProcessingUrl("/superuser/superlogin")
                    .failureUrl("/login-super?error=true").defaultSuccessUrl("/superuser", true).permitAll().and()
                    .logout().logoutUrl("/superuser/logout-super") // If csrf is enabled then logout must be post
                    .logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID", "XSRF-TOKEN");

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
            http.antMatcher("/user/**").authorizeRequests().anyRequest().hasAuthority(AvailableRoles.Code.USER).and()
                    .formLogin().loginPage("/login-user").loginProcessingUrl("/user/userlogin")
                    .failureUrl("/login-user?error=true").defaultSuccessUrl("/user", true).permitAll().and().logout()
                    .logoutUrl("/user/logout-user").logoutSuccessUrl("/").invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "XSRF-TOKEN");

        }

    }

    @Configuration
    public static class GuestSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/foo").authenticated().anyRequest().permitAll();

            /**
             * We need to secure api enpoints as they are not secured by defaul;t include
             * the below line before pushing it to production
             */
            // .antMatchers("/api/superuser/**")
            // .hasAuthority(AvailableRoles.Code.SUPERUSER)

            // .antMatchers("/orders/pool")
            // .hasAnyAuthority(AvailableRoles.Code.CUSTOMER_CARE,
            // AvailableRoles.Code.SUPERUSER)

        }
    }

    @Bean
    public static PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
