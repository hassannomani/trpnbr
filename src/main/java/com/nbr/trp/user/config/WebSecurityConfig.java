package com.nbr.trp.user.config;

import com.nbr.trp.user.filter.AuthEntryPointJwt;
import com.nbr.trp.user.filter.AuthTokenFilter;
import com.nbr.trp.user.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http.cors().and().csrf().disable().exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                //.authorizeRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/test/**").permitAll()
                .requestMatchers("/api/action/message/**").permitAll()
                .requestMatchers("/api/common/file/**").permitAll()
                .requestMatchers("/api/certificate/check/**").permitAll()
                .requestMatchers("/api/certificate/get/**").permitAll()
                .requestMatchers("/api/etin/tin/**").permitAll()
                .requestMatchers("/api/otp/**").permitAll()
                .requestMatchers("/api/common/district").permitAll()
                .requestMatchers("/api/common/division").permitAll()
                .requestMatchers("/api/common/thana").permitAll()
                .requestMatchers("/api/common/bank").permitAll()
                .requestMatchers("/api/common/bankbranches").permitAll()
                .requestMatchers("/api/common/bankdist").permitAll()
                .requestMatchers("/api/common/citycorporation").permitAll()
                .requestMatchers("/api/agent/allfront").permitAll()
                .requestMatchers("/api/users/roles").permitAll()
                .requestMatchers("/api/users/register").permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        return authenticationConfiguration
                .getAuthenticationManager();
    }
}
