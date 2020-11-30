package com.todoproject.todo.Filter;

import com.todoproject.todo.Model.User;
import com.todoproject.todo.repositories.UserRepository;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TokenFilter extends OncePerRequestFilter {

  @Autowired
  UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    if (!request.getRequestURI().contains("/api/v1/user/")) {
      String token = request.getHeader("Authorization");
      if (token == null || token.isBlank() || token.isEmpty()) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("No Auth Token");
        response.getWriter().flush();
        response.getWriter().close();
        return;
      }
      String userId = token.split("\\|")[0];
      String password = token.split("\\|")[1];
      if (userRepository.findById(userId).isEmpty()) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write("Invalid Auth Token");
        response.getWriter().flush();
        response.getWriter().close();
        return;
      }
      else{
        User user = userRepository.findById(userId).get();
        if(!user.getPassword().equals(password)){
          response.setStatus(HttpStatus.FORBIDDEN.value());
          response.getWriter().write("Invalid Auth Token");
          response.getWriter().flush();
          response.getWriter().close();
          return;
        }
      }

      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
          userId, null, new ArrayList<>());
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    chain.doFilter(request, response);
  }
}
