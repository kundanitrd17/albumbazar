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

@EnableWebSecurity
public class SecurityConfig{


    @Configuration
    @Order(1)
    public static class SuperuserSecurityConfig extends WebSecurityConfigurerAdapter {

        private UserDetailsService superuserDetailsService;

        @Autowired
        public SuperuserSecurityConfig(@Qualifier("superuserDetailsService") UserDetailsService userDetailsService){
            this.superuserDetailsService = userDetailsService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(superuserDetailsService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .antMatcher("/superuser/**")
                .authorizeRequests()
                    .antMatchers("/superuser/**").hasRole("SUPERUSER")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login-super")
                    .loginProcessingUrl("/superuser/superlogin")
                    .failureUrl("/login-super?error=true")
                    .defaultSuccessUrl("/superuser")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/superuser/logout-super")  // If csrf is enabled then logout must be post
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);
        }
    }

   @Configuration
   @Order(2)
   public static class UserSecurityConfig extends WebSecurityConfigurerAdapter {

       private UserDetailsService normalUserDetailsService;

       @Autowired
       public UserSecurityConfig(@Qualifier("normalUserDetailsService") UserDetailsService userDetailsService) {
            this.normalUserDetailsService = userDetailsService;
       }

       @Override
       protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           auth.userDetailsService(normalUserDetailsService);
       }

       @Override
       protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .antMatcher("/user/**")
                .authorizeRequests()
                    .antMatchers("/user/**").hasAnyRole("SUPERUSER", "USER")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login-user")
                    .loginProcessingUrl("/user/userlogin")
                    .failureUrl("/login-user?error=true")
                    .defaultSuccessUrl("/user")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/user/logout-user")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);
                   
       }

   }

    @Bean
    public static PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
