package com.example.backend_uppgift.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                .authorizeHttpRequests((requests)-> requests
                        .requestMatchers("/","/rooms/all","/js/**","/css/**","/images/**","/login/**","/logout/","/queues/**","/reset-password/**").permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin((form)-> form
                        .permitAll()
                        .defaultSuccessUrl("/",true)
                        .failureUrl("/login?error=true")
                        .permitAll())

                .logout((logout)-> {
                    logout.permitAll();
                    logout.logoutSuccessUrl("/");
                })
                .exceptionHandling((exceptions) -> exceptions
                        .accessDeniedPage("/access-denied")
                );;
        return http.build();
    }

}
