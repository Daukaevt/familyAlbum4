package com.wixsite.mupbam1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests(auth->{
			auth.requestMatchers("/album").permitAll();
			auth.anyRequest().authenticated();
		})
		.formLogin(withDefaults())
		.oauth2Login(withDefaults());
		return http.build();		
	} 

}
