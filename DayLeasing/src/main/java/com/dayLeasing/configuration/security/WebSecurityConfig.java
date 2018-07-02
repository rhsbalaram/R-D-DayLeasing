package com.dayLeasing.configuration.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// TODO: Auto-generated Javadoc

/**
 * The Class WebSecurityConfig.
 *
 * @author Balaram
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  /** The user details service. */
  @Autowired
  @Qualifier("userDetailsService")
  UserDetailsService userDetailsService;

  /*
   * (non-Javadoc)
   * @see
   * org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
   * #configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/")
        .permitAll()
        .antMatchers("/registration")
        .permitAll()
        .antMatchers("/check")
        .permitAll()
        .antMatchers("/sendVerification")
        .permitAll()
        .antMatchers("/connect/**")
        .permitAll()
        .antMatchers(HttpMethod.POST,"/login")
        .permitAll()
        .antMatchers(HttpMethod.GET,"/hunter/**")
        .permitAll()
        .antMatchers(HttpMethod.POST,"/hunter/reservation")
        .permitAll()
        .antMatchers("/user/**")
        .hasAuthority("USER")
        .antMatchers("/hunter/**")
        .hasAuthority("HUNTER")
        .anyRequest()
        .authenticated()
        .and()
        // We filter the api/login requests
        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
            UsernamePasswordAuthenticationFilter.class)
        // And filter other requests to check the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**", "/v2/api-docs", "/configuration/ui",
        "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**",
        "/images/**"); // #3

  }

  /*
   * (non-Javadoc)
   * @see
   * org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
   * #configure(org.springframework.security.config.annotation.authentication.builders.
   * AuthenticationManagerBuilder)
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Create a default account to test
    auth.userDetailsService(userDetailsService);
  }

  /**
   * Password encoder.
   *
   * @return the password encoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }
}