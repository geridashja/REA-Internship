package com.todo.todo.config;

import com.todo.todo.entity.todouser;
import com.todo.todo.repository.todouserrepo;
import com.todo.todo.services.todouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;


@EnableWebSecurity
public class auth extends WebSecurityConfigurerAdapter {
    @Autowired
    todouserrepo userRepository;

    @Resource
    todouserService todouserservice;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/login", "/register")
                .permitAll()
                .antMatchers("/user/**").hasAuthority("todouser")
                .and()
                .formLogin(form -> form
                        .defaultSuccessUrl("/userhome")
                        .loginPage("/login")
                        .failureUrl("/login?error")
                )
                .logout()
                .logoutSuccessUrl("/index");
        ;
    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//    }
    @Bean
    public DaoAuthenticationProvider authprovider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(todouserservice);
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authprovider());
    }

    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}
