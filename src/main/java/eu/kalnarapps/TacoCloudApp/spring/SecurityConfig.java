package eu.kalnarapps.TacoCloudApp.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class SecurityConfig {

    @Value("${TACO_CLOUD_APP_ADMIN_IP}")
    private String adminIp;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("Admin IP: " + adminIp);
        return http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                PathRequest.toH2Console()
                        )
                )
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers("/admin", "/admin/register").access(
                                        new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasIpAddress('" + adminIp + "')")
                                )
                                .requestMatchers("/design", "/orders", "/orders/**").access(
                                        new WebExpressionAuthorizationManager("hasRole('USER') or hasAuthority('OIDC_USER')")
                                )
                                .requestMatchers("/", "/login", "/register", "/images/**", "/styles.css", "/h2-console/**").access(
                                        new WebExpressionAuthorizationManager("permitAll()")
                                )
                                .anyRequest().authenticated()
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/design")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/login") // Optional: Custom login page
                                .defaultSuccessUrl("/design", true) // Redirect after successful login
                                .failureUrl("/login?error") // Redirect on login failure
                )
                .logout(
                        logout -> logout.logoutSuccessUrl("/").permitAll()
                )
                .build();
    }
}