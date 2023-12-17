//package com.missworld.missweb.Security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class Security {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.csrf().disable()
//                .authorizeRequests()
//                .requestMatchers("/loginUser","/signup","/css/**","/js/**")
//                .permitAll()
//                .and()
//                .formLogin(form -> form
//                        .loginPage("/loginUser")
//                        .usernameParameter("email")
//                        .permitAll()
//                        .defaultSuccessUrl("/dashBoard")
//                        .loginProcessingUrl("/loginUser")
////                        .failureUrl("/loginUser?error=true")
//                )
//                .logout(logout -> logout
//                                  .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());
//        return http.build();
//    }
//}
