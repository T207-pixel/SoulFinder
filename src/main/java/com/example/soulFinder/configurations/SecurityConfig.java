package com.example.soulFinder.configurations;

import com.example.soulFinder.configurations.securityhandlers.CustomUsernamePasswordAuthenticationFilter;
import com.example.soulFinder.services.CustomUserDetailsService;
import com.example.soulFinder.configurations.securityhandlers.CustomAuthenticationFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.RedirectStrategy;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final CustomUserDetailsService userDetailsService;

    private final CustomAuthenticationFailureHandler failureHandler;

    private CustomUsernamePasswordAuthenticationFilter authenticationFilter;

    public RedirectStrategy redirectStrategy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/post/**", "/images/**", "/registration", "/user/**", "/static/**") // ** - любая строка
                .permitAll()
                .anyRequest().authenticated()
                .and()
//                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
//                .failureHandler(failureHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
