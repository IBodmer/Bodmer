package com.task.bodmer.config;

import com.task.bodmer.security.AuthProviderImpl;
import com.task.bodmer.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthProviderImpl authProvider; // удалить
    private final UserServiceImpl userService;

    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider, UserServiceImpl userService) {
        this.authProvider = authProvider;
        this.userService = userService;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/api/v1/auth/login", "/error").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
////                .loginPage("/api/v1/auth/login")
//                .loginPage("/login")
////                .loginProcessingUrl("/process_login")
//                .defaultSuccessUrl("/api/v1/profile", true)
//                .failureUrl("/api/v1/auth/login?error")
//                .and()
//                .logout()
//                .logoutUrl("/api/v1/logout")
//                .logoutSuccessUrl("/api/v1/auth/login");
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }




}
