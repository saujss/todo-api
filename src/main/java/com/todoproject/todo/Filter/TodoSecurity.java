package com.todoproject.todo.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class TodoSecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  TokenFilter tokenFilter;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.cors().and().authorizeRequests().antMatchers("/api/v1/user/**").permitAll()
        .anyRequest().authenticated().and().httpBasic()
        .and().csrf().disable().sessionManagement().sessionFixation().migrateSession()
        .sessionCreationPolicy(
            SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);
  }
}
