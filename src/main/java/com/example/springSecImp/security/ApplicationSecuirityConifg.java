package com.example.springSecImp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.springSecImp.security.ApplicationUserPermission.COURSE_WRITE;
import static com.example.springSecImp.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecuirityConifg extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecuirityConifg(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .antMatchers(HttpMethod.DELETE,"/management/api/v1/students").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.POST,"/management/api/v1/students").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.PUT,"/management/api/v1/students").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.GET,"/management/api/v1/students").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails rahulUser = User.builder()
                .username("rahul")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name())  //ROLE_STUDENT
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .roles(ADMIN.name()) //ROLE_ADMIN
                .build();

        UserDetails adminTrainee = User.builder()
                .username("adminTrainee")
                .password(passwordEncoder.encode("password"))
                .roles(ADMINTRAINEE.name()) //ROLE_ADMIN_trainee
                .build();

        return new InMemoryUserDetailsManager(
                rahulUser , admin, adminTrainee
        );
    }

}

