package com.knowledgeCenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("sudha").password("{noop}sudha").roles("AUTHOR");
		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("REVIEWER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().disable();
		http.authorizeRequests().antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
				.permitAll().antMatchers(HttpMethod.POST, "/api/v1/document/**").hasRole("AUTHOR").anyRequest()
				.authenticated().and().formLogin();
	}
}
