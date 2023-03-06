package com.njoytoyshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService service;
	
	@Bean
	PasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}



	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(service).passwordEncoder(encode());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/register").antMatchers("/save1");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
	
		.antMatchers("/add").hasAnyRole("ADMIN")
		.antMatchers("/home").hasAnyRole("ADMIN","USER")
		.antMatchers("/access").hasAnyRole("USER","ADMIN")
		.antMatchers("/update/{id}").hasAnyRole("ADMIN")
		.antMatchers("/delete/{id}").hasAnyRole("ADMIN")
		.antMatchers("/updatetoy/{id}").hasAnyRole("ADMIN")
		.and()
		
		.formLogin().loginPage("/login").permitAll().and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/home");
		
		http.exceptionHandling().accessDeniedPage("/access");
	}

}
