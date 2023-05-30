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
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/profile/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/", "GET").permitAll()
                .requestMatchers("/products", "GET").permitAll()
                .requestMatchers("/product/{id}").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/shoppingcart").permitAll()
                .requestMatchers("/resetpassword").permitAll()
                .requestMatchers("/forgotpassword").permitAll()
                .requestMatchers("/signup-form").permitAll()
                .requestMatchers("/signup-success").permitAll()
                .requestMatchers("/no-access").permitAll()

                .requestMatchers("/api/cartItems/getAll").hasRole("ADMIN")
                .requestMatchers("/api/cartItems/{id}").hasAnyRole("USER","ADMIN")
                .requestMatchers("/api/cartItems/add").hasAnyRole("USER","ADMIN")
                .requestMatchers("/api/cartItems/update({id}").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/cartItems/remove/{id}").hasAnyRole("USER", "ADMIN")


                .requestMatchers("/api/category/getAll").permitAll()
                .requestMatchers("/api/category/{id}").permitAll()
                .requestMatchers("/api/category/add").hasRole("ADMIN")
                .requestMatchers("/api/category/update/{id}").hasRole("ADMIN")
                .requestMatchers("/api/category/remove/{id}").hasRole("ADMIN")


                .requestMatchers("/api/order/getAll").hasRole("ADMIN")
                .requestMatchers("/api/order/{id}").hasAnyRole("USER","ADMIN")
                .requestMatchers("/api/order/add").hasAnyRole("USER","ADMIN")
                .requestMatchers("/api/order/update/{id}").hasRole("ADMIN")
                .requestMatchers("/api/order/remove{id}").hasRole("ADMIN")

                .requestMatchers("/api/orderProduct/getAll").hasRole("ADMIN")
                .requestMatchers("/api/orderProduct/add").hasAnyRole("USER","ADMIN")

                .requestMatchers("/api/product/getAll").permitAll()
                .requestMatchers("/api/product/{id}").permitAll()
                .requestMatchers("/api/product/name{name}").permitAll()
                .requestMatchers("/api/product/getByCategory{category}").permitAll()
                .requestMatchers("/api/product/add").hasRole("ADMIN")
                .requestMatchers("/api/product/update/{id}").hasRole("ADMIN")
                .requestMatchers("/api/product/remove/{id}").hasAnyRole("ADMIN")

                .requestMatchers("/api/role/getAll").permitAll()
                .requestMatchers("/api/role/{id}").permitAll()
                .requestMatchers("/api/role/name{name}").permitAll()
                .requestMatchers("/api/role/add").hasRole("ADMIN")
                .requestMatchers("/api/role/update/{id}").hasRole("ADMIN")
                .requestMatchers("/api/role/remove/{id}").hasRole("ADMIN")

                .requestMatchers("/api/shoppingcart/getAll").hasRole("ADMIN")
                .requestMatchers("/api/shoppingcart/{id}").hasAnyRole("USER","ADMIN")
                .requestMatchers("/api/shoppingcart/add").hasRole("ADMIN")
                .requestMatchers("/api/shoppingcart/update/{id}").hasRole("ADMIN")
                .requestMatchers("/api/shoppingcart/remove/{id}").hasRole("ADMIN")

                .requestMatchers("/api/user/getAll").hasRole("ADMIN")
                .requestMatchers("/api/user/{id}").hasAnyRole("USER","ADMIN")
                .requestMatchers("/api/user/add").permitAll()
                .requestMatchers("/api/user/update/{id}").hasAnyRole("USER","ADMIN")
                .requestMatchers("/api/user/remove/{id}").hasRole("ADMIN")

                .and().formLogin().loginPage("/login")
                .and().logout().logoutSuccessUrl("/");
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
