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

    /**
     * Configuration for Superuser security
     */
    @Configuration
    @Order(1)
    public static class SuperuserSecurityConfig extends WebSecurityConfigurerAdapter {

        // UserDetailService interface required by Spring security to authenticate users
        private final UserDetailsService superuserDetailsService;

        // Injecting superuser service as it contains the implementation of
        // UserDetailService interface
        // Contains login to authenticate
        @Autowired
        protected SuperuserSecurityConfig(@Qualifier("superuserService") final UserDetailsService userDetailsService) {
            this.superuserDetailsService = userDetailsService;
        }

        // Using Spring data JPA to access the database for authentication and thus
        // using setting AuthenticationMangerBuidler to required service
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(superuserDetailsService);
        }

        // Securing endpoints of the superuser
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/superuser/**").authorizeRequests().antMatchers("/superuser/js/*.js").permitAll()
                    .antMatchers("/superuser/css/*.css").permitAll() // remove in production
                    .anyRequest().hasAuthority(AvailableRoles.Code.SUPERUSER).and().formLogin()
                    .loginPage("/login-super").loginProcessingUrl("/superuser/superlogin")
                    .failureUrl("/login-super?error=true").defaultSuccessUrl("/superuser").permitAll().and().logout()
                    .logoutUrl("/superuser/logout-super") // If csrf is enabled then logout must be post
                    .logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID", "XSRF-TOKEN");

        }

    }

    // // /**
    // // * Security configuration for authenticating Customer care endpoints
    // // */
    // @Configuration
    // @Order(2)
    // public static class CustomerCareSecurityConfig extends
    // WebSecurityConfigurerAdapter {

    // // Even the customer care in an employee, thus we can access it using
    // // customer-care service
    // private final UserDetailsService employeeService;

    // @Autowired
    // protected CustomerCareSecurityConfig(
    // @Qualifier("employeeService") final UserDetailsService userDetailsService) {
    // this.employeeService = userDetailsService;
    // }

    // @Override
    // protected void configure(final AuthenticationManagerBuilder auth) throws
    // Exception {
    // auth.userDetailsService(employeeService);
    // }

    // @Override
    // protected void configure(final HttpSecurity http) throws Exception {
    // http.antMatcher("/customer-care/**").authorizeRequests().anyRequest()
    // .hasAuthority(AvailableRoles.Code.CUSTOMER_CARE).and().formLogin().loginPage("/customer-care/login")
    // .loginProcessingUrl("/customer-care/login").failureUrl("/customer-care/login?error=true")
    // .defaultSuccessUrl("/customer-care").permitAll().and().logout().logoutUrl("/customer-care/logout")
    // .logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID",
    // "XSRF-TOKEN");

    // }

    // }

    // /**
    // * Securing endpoints for the Delivery EndPoint
    // */
    // @Configuration
    // @Order(3)
    // public static class DeliverySecurityConfig extends
    // WebSecurityConfigurerAdapter {

    // // Even the Delivery agent is an employee, thus we can access it using
    // // customer-care service
    // private final UserDetailsService employeeService;

    // @Autowired
    // protected DeliverySecurityConfig(@Qualifier("employeeService") final
    // UserDetailsService userDetailsService) {
    // this.employeeService = userDetailsService;
    // }

    // @Override
    // protected void configure(final AuthenticationManagerBuilder auth) throws
    // Exception {
    // auth.userDetailsService(employeeService);
    // }

    // @Override
    // protected void configure(final HttpSecurity http) throws Exception {
    // http.antMatcher("/delivery/**").authorizeRequests().anyRequest().hasAuthority(AvailableRoles.Code.DELIVERY)
    // .and().formLogin().loginPage("/delivery/login").loginProcessingUrl("/delivery/login")
    // .failureUrl("/delivery/login-user?error=true").defaultSuccessUrl("/delivery",
    // true).permitAll()
    // .and().logout().logoutUrl("/delivery/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
    // .deleteCookies("JSESSIONID", "XSRF-TOKEN");

    // }

    // }

    // /**
    // * Securing endpoints for customer
    // */

    @Configuration
    @Order(4)
    public static class CustomerSecurityConfig extends WebSecurityConfigurerAdapter {

        private final UserDetailsService customService;

        @Autowired(required = true)
        protected CustomerSecurityConfig(@Qualifier("customerService") final UserDetailsService customerService) {
            this.customService = customerService;
        }

        @Override
        protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/customer/**").authorizeRequests().anyRequest().hasAuthority(AvailableRoles.Code.USER)
                    .and().formLogin().loginPage("/").loginProcessingUrl("/customer/login").failureUrl("/")
                    .defaultSuccessUrl("/", true).permitAll().and().logout().logoutUrl("/customer/logout")
                    .logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID", "XSRF-TOKEN");

        }

    }

    // /**
    // * Securing Rest Endpoints
    // */
    @Configuration
    @Order(5)
    public static class RestEndpointsSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/secured/**").authorizeRequests().antMatchers("/api/secured/customer/**")
                    .hasAuthority(AvailableRoles.Code.USER).antMatchers("/api/secured/customer-care/**")
                    .hasAuthority(AvailableRoles.Code.CUSTOMER_CARE);

        }

    }

    /**
     * Securing Remaining endpoints
     */
    @Configuration
    public static class GuestSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("**/js/**", "**/css/**").authenticated().anyRequest().permitAll();

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
