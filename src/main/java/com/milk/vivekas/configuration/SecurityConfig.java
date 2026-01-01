package com.milk.vivekas.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	 private final JwtAuthenticationFilter jwtAuthFilter;

	    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
	        this.jwtAuthFilter = jwtAuthFilter;
	    }

//	    @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        return http
//	                .csrf(AbstractHttpConfigurer::disable)
//	                .authorizeHttpRequests(auth -> auth
//	                        .requestMatchers("/api/users/register", "/api/users/login").permitAll()
//	                        .anyRequest().authenticated()
//	                )
//	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//	                .build();
//	    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	http
			// disable CSRF for APIs (fixes 403 crumb error)
			.csrf(csrf -> csrf
					.ignoringRequestMatchers("/api/**")
			)

			// allow unauthenticated access to login & register
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(
							"/api/users/register",
							"/api/users/login"
					).permitAll()

					// everything else requires authentication
					.anyRequest().authenticated()
			)

			// stateless JWT session
			.sessionManagement(session ->
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)

			// add JWT filter before UsernamePasswordAuthenticationFilter
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

	return http.build();
}




	/*
		 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
		 * throws Exception { return http .csrf().disable() .authorizeHttpRequests(auth
		 * -> auth .requestMatchers("/api/users/register").permitAll()
		 * .anyRequest().authenticated() ) .httpBasic() // instead of formLogin .and()
		 * .build(); ee}
		 */
    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    
    
}
