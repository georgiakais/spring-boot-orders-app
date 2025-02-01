package com.mydata.authentication;

import com.mydata.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final CustomUserDetailsServiceImpl customUserDetailsService;

    public SecurityConfig(CustomUserDetailsServiceImpl customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( "/css/**", "/js/**", "/api/users/**", "/api/orders/**").permitAll() // Allow unauthenticated access to login and API
                .anyRequest().authenticated()  // Requires authentication for other requests
                .and()
                .formLogin()
                .failureUrl("/login?error=true")  // Redirect to login with error if authentication fails
                .defaultSuccessUrl("/dashboard", true)  // Redirect to dashboard after successful login
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")  // URL for logging out
                .logoutSuccessUrl("/login")  // Redirect to login after logout
                .permitAll();  // Allow everyone to logout

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
