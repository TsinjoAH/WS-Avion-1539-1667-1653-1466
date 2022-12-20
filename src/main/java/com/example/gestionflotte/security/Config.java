package com.example.gestionflotte.security;

import com.example.gestionflotte.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
@Order(1)
public class Config extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginService login;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Bearer b=new Bearer();
        b.setAuthenticationManager(new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String key=(String)authentication.getPrincipal();
                if(!login.isConnected(key)){
                    throw new BadCredentialsException("Api key needed, access denied");
                }
                authentication.setAuthenticated(true);
                return authentication;
            }
        });
        http.mvcMatcher("/planes/**")
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(b)
                .addFilterBefore(new ExceptionTranslationFilter(new Http403ForbiddenEntryPoint()),b.getClass())
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
