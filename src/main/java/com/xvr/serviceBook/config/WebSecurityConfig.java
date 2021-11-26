package com.xvr.serviceBook.config;

import com.xvr.serviceBook.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;
    private final DataSource dataSource;
    private final AppUserService appUserService;

    @Autowired
    public WebSecurityConfig(AccessDeniedHandler accessDeniedHandler, DataSource dataSource,@Lazy AppUserService appUserService) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.dataSource = dataSource;
        this.appUserService = appUserService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Authentication Manager Service to find User in the database.
        // And Setting PasswordEncoder
        auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http
                .authorizeRequests()
                    .antMatchers("/", "/web/appusers","web/appusers/create-user","/web/appusers/user-add-successful").permitAll()
                    .antMatchers("/h2-console/**").hasRole("ADMIN")
                    .antMatchers("/users/**").hasRole("USER")
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
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)        // set invalidation state when logout
                    .deleteCookies("JSESSIONID")
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

        //Confgure Token Remember Me
        /*http.authorizeRequests()
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
