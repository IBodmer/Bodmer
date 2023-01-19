package com.task.bodmer.config;

import com.task.bodmer.service.UserDetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetService userDetService;

    @Autowired
    public SecurityConfig(UserDetService userDetService) {
        this.userDetService = userDetService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/api/v1/auth/login","/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/api/v1/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/api/v1/profile", true)
                .failureUrl("/api/v1/auth/login?error");
    }

    //сама аутентификация. логика
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
