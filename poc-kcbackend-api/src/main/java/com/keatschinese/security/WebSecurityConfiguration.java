package com.keatschinese.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.keatschinese.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private Logger logger = LoggerFactory.getLogger(WebSecurityConfiguration.class);
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("enter configure AuthenticationManagerBuilder method!");
		auth.userDetailsService(userDetailsService); 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("enter configure HttpSecurity method!");
		http.authorizeRequests()
			.antMatchers("/v1/api/regists").permitAll()
			.anyRequest().hasAuthority("api-user"); //.authenticated();
		http.httpBasic();
		http.csrf().disable();
	}
}
