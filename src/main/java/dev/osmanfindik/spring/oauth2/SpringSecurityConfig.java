package dev.osmanfindik.spring.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests (authorizeHttpRequests -> authorizeHttpRequests
						.requestMatchers ("/", "/index.html", "/error", "/webjars/**").permitAll ()
						.anyRequest ().authenticated ())
				.exceptionHandling (e -> e.authenticationEntryPoint (new HttpStatusEntryPoint (HttpStatus.UNAUTHORIZED)))
				.oauth2Login (Customizer.withDefaults ());
		return http.build ();
	}
}
