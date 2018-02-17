package com.HFTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
					.authorizeRequests().antMatchers("/shops").permitAll().and()
					.authorizeRequests().antMatchers("/login").permitAll();
		
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}
