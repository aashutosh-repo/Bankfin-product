package com.dev.jpa.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        //write a query to retrieve a use by usrname
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select USERID,PASSWORD,STATUS from CHANNEL_USERS where USERID = ?"
        );

        //write query to retrieve Roles of user
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select USERID,USER_ROLE from USER_ROLE where USERID = ?"
        );

        return jdbcUserDetailsManager;
    }
	

	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception{
//	    http.authorizeHttpRequests(configurer ->
//	    configurer.anyRequest().authenticated())
//	      .formLogin(form ->
//	      form.loginPage("/loginpge")
//	      .loginProcessingUrl("/authenticateTheUser")
//	      .permitAll());
//		return http.build();
        http.authorizeHttpRequests(configurer ->
        configurer
                .requestMatchers(HttpMethod.GET,"/").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/rest-jpa/students/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST,"/rest-jpa/students").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT,"/rest-jpa/students").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/rest-jpa/students/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"student/add").hasRole("MANAGER")
                .anyRequest().authenticated()
                ).formLogin(form ->
      	      form.loginPage("/loginpge")
      	      .loginProcessingUrl("/authenticateTheUser")
      	      .permitAll()
      	      );
        	http.httpBasic(Customizer.withDefaults());
        	http.csrf(csrf -> csrf.disable());
        return http.build();
	    }

}
