package com.codeup.codeupspringblog;
import com.codeup.codeupspringblog.services.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


//Finally, we need to create a configuration class to determine several things:
//
//Which pages will require authentication
//Which pages are available to anyone
//The path to the login page
//The hashing algorithm we'll use to store passwords

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
    private UserDetailsLoader usersLoader;

        public SecurityConfiguration(UserDetailsLoader usersLoader) {
            this.usersLoader = usersLoader;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    /* Login configuration */
                    .formLogin()
                    .loginPage("/home")
                    .defaultSuccessUrl("/posts") // user's home page, it can be any URL
                    .permitAll() // Anyone can go to the login page
                    /* Logout configuration */
                    .and()
                    .logout()
                    .logoutSuccessUrl("/home") // append a query string value
                    /* Pages that can be viewed without having to log in */
                    .permitAll()// allow all users to access the logout page
                    .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/home", "/posts", "/users/signup", "/logout") // anyone can see the home and the ads pages
                    .permitAll()
                    /* Pages that require authentication */
                    .and()
                    .authorizeHttpRequests()
                    .requestMatchers(
                            "/posts/create", "/posts/{id}", "/posts/{id}/edit", "/posts/{id}/delete" // only authenticated users can create ads
                            // only authenticated users can edit ads
                    )
                    .authenticated()
            ;
            return http.build();
        }
}

