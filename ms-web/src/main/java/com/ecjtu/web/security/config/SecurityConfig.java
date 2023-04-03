package com.ecjtu.web.security.config;

import com.ecjtu.web.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description: spring security 配置
 * @Author: ZengXueBin
 * @Date: 2023/4/2 20:08
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * 自定义用户认证逻辑
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * token认证过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    /**
     * 加密实现
     * @return BCrypt
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 解决 无法直接注入 AuthenticationManager
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // CSRF禁用 不使用session
                .csrf().disable()
                // 基于token 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/**").permitAll()
                    // 除上面外的所有请求全部需要鉴权认证
                    .anyRequest().authenticated())
                // 添加jwt filter
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
