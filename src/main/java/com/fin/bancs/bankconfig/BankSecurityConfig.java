package com.fin.bancs.bankconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class BankSecurityConfig {
	
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by USERID
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select USERID, PASSWORD, STATUS from channel_users where USERID=?");

        // define query to retrieve the authorities/roles by USERID
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select USERID, user_role from user_role where USERID=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST,"/customer-service").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET,"/nominee").hasRole("EMPLOYEE")
                                .requestMatchers("/instrument").hasRole("MANAGER")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .requestMatchers("/v2/api-docs",    // Swagger API documentation
                                        "/swagger-resources/**",    // Swagger resource endpoints
                                        "/swagger-ui.html",         // Swagger UI
                                        "/webjars/**"   ).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );
        http
        .csrf((csrf) -> csrf.disable());
        return http.build();
    }

}
