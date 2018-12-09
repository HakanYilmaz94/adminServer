package com.server.adminServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@Configuration
@SpringBootApplication
public class AdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerApplication.class, args);
	}

	@Configuration
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests()
					.antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**").permitAll().anyRequest()
					.authenticated().and().formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll()
					.and().logout().logoutUrl("/logout").and().httpBasic();
		}
	}
}
