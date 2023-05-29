package no.ntnu.ProFlex.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Creates AuthenticationManager - set up authentication type
 * The @EnableWebSecurity tells that this ia a class for configuring web security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    /**
     * A service providing our users from the database
     */
    @Autowired
    UserDetailsService userDetailsService;

    /**
     * This method will be called automatically by the framework to find out what authentication to use.
     * Here we tell that we want to load users from a database
     *
     * @param auth Authentication builder
     * @throws Exception
     */
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * This method will be called automatically by the framework to find out what authentication to use.
     *
     * @param http HttpSecurity setting builder
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain configureAuthorizationFilterChain(HttpSecurity http) throws Exception {
        // Set up the authorization requests, starting from most restrictive at the top, to least restrictive on bottom
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/profile/**")).hasAnyRole("USER", "ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/", "GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/products", "GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/product/{id}")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/about")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/shoppingcart")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/resetpassword")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/forgotpassword")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/signup-form")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/signup-success")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/no-access")).permitAll()
                .and().formLogin().loginPage("/login")
                .and().logout().logoutSuccessUrl("/")
        ;
        http.csrf().disable().formLogin((form) -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll()
        );
        return http.build();
    }


    /**
     * This method is called to decide what encryption to use for password checking
     *
     * @return The password encryptor
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
