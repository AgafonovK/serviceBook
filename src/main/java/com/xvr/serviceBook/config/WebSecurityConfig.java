package com.xvr.serviceBook.config;

import com.xvr.serviceBook.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;

    private final DataSource dataSource;

    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public WebSecurityConfig(AccessDeniedHandler accessDeniedHandler, DataSource dataSource, UserDetailServiceImpl userDetailService) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.dataSource = dataSource;
        this.userDetailService = userDetailService;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder (){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();

        http.authorizeRequests()
            .antMatchers("/actuator/**","/login", "/logout", "/register")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/userInfo","/ticket/**","/report/**").hasAuthority("USER")
                .and()
                .authorizeRequests().antMatchers("/admin","/equipment/**","/department/**","/worker/**").hasAuthority("ADMIN")
                .anyRequest().authenticated();


        http.authorizeRequests()
                .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
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
                .tokenValiditySeconds(60*60); // 1 hour token;
       // http.headers().frameOptions().disable();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}
