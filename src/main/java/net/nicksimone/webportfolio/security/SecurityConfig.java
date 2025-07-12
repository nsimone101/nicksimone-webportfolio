package net.nicksimone.webportfolio.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/pizza/employee-login", "/css/**").permitAll()
                .requestMatchers("/pizza/employee-homepage").hasRole("EMPLOYEE")
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/pizza/employee-login")
                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/pizza/employee-homepage", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/pizza/employee-login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery(
            "SELECT user_id, pw, active FROM employees WHERE user_id = ?");
        manager.setAuthoritiesByUsernameQuery(
            "SELECT user_id, role FROM employees e JOIN roles r ON e.employee_id = r.employee_id WHERE user_id = ?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}