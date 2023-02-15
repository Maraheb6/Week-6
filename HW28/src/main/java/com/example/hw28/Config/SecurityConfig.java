package com.example.hw28.Config;

import com.example.hw28.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/user/register").permitAll()
                .requestMatchers("/api/v1/user/update" ,"/api/v1/user/get-all-order/**" , "/api/v1/user/get-customer/**" ,
                        "/api/v1/user/login","/api/v1/order/add/**","api/v1/order/delete/**","api/v1/order/get-order/**","/api/v1/user/my-orders/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/user/delete" , "/api/v1/user/get-all-order/**" ,
                        "/api/v1/user/get-customer/**" , "/api/v1/user/login" ,
                        "/api/v1/order/get" , "/api/v1/order/add/**","/api/v1/order/update/**","api/v1/order/get-order/**","api/v1/product/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}
