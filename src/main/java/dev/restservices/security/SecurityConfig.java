package dev.restservices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
				.withUser("admin").password("adminpass").roles("ADMIN")
				 .and()
				.withUser("user").password("userpass").roles("USER");
			  /*.and()
				.withUser("guest").password("guest").roles("GUEST");*/		
	}
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.httpBasic()
            .and()
     		 .csrf().disable()
    	    .authorizeRequests()
    	     .antMatchers(HttpMethod.GET,"/movies").hasAnyRole("ADMIN","USER")
    	     .antMatchers(HttpMethod.POST,"/movies").hasRole("ADMIN")
     	     .antMatchers(HttpMethod.POST,"/movies/*/comments").hasRole("USER");
    }
 
}
