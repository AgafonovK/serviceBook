package com.xvr.serviceBook.config;

import com.xvr.serviceBook.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private final DataSource dataSource;
    @Autowired
    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public WebSecurityConfig(AccessDeniedHandler accessDeniedHandler, DataSource dataSource, UserDetailServiceImpl userDetailService) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.dataSource = dataSource;
        this.userDetailService = userDetailService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Authentication Manager Service to find User in the database.
        // And Setting PasswordEncoder
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http
                .authorizeRequests()
                    .antMatchers("/", "/register").permitAll()
                    .antMatchers("/admins/**", "/h2-console/**").hasRole("ADMIN")
                    .antMatchers("/users/**").hasRole("USER")
                    //.antMatchers("/h2-console","/h2-console/**").access("hasRole('ADMIN')")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/userInfo")
                    .failureUrl("/login?error=true")
                    .permitAll()
                .and()
                    .logout()
                    //.logoutUrl("/logout")
                    //.logoutSuccessUrl("/")
                    //.invalidateHttpSession(true)        // set invalidation state when logout
                    //.deleteCookies("JSESSIONID")
                    .permitAll()
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")
                    .accessDeniedHandler(accessDeniedHandler)
                .and()
                    .rememberMe()
                    .tokenRepository(this.persistentTokenRepository())
                    .tokenValiditySeconds(60*60) // 1 hour token;*/
                .and()
                    .httpBasic();

        /*http.authorizeRequests()
                .antMatchers("/admin","/equipment/**","/department/**","/worker/**","/h2-console/**").hasAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/userInfo","/ticket/**","/report/**").hasAuthority("USER")
                .and()
                .authorizeRequests().antMatchers("/actuator/**","/login", "/register", "/h2-console/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated();


        http.authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/userInfo")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccessful")
                .invalidateHttpSession(true);

        http.authorizeRequests()
                .and()
                .exceptionHandling()
                //.accessDeniedPage("/403");
                .accessDeniedHandler(accessDeniedHandler);

        //Confgure Token Remember Me
        http.authorizeRequests()
                .and()
                .rememberMe()
                .tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(60*60); // 1 hour token;*/
        // http.headers().frameOptions().disable();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}
