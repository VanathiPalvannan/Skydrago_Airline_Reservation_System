package com.skydrago.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService service;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(service);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/userregister","/saveuserreg","/","/img/**","/home","/homesearch","/aboutus").permitAll()
		.antMatchers("/input","/userview","/searchUser/**").hasAnyAuthority("ADMIN")
		.antMatchers("/passengersearch").hasAnyAuthority("STAFF")
		.antMatchers("/view").hasAnyAuthority("USER","ADMIN","STAFF")
		.antMatchers("/edit/**").hasAnyAuthority("ADMIN")
		.antMatchers("/delete/**").hasAnyAuthority("ADMIN")
		.antMatchers("/bookdetail/**","/seats/**","/storePassenger/**","/viewBookedDetail/**").hasAnyAuthority("USER")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/home").permitAll()
		.and()
		.logout().invalidateHttpSession(false)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout-success").permitAll();
	}
	
	
	
	
	
}
