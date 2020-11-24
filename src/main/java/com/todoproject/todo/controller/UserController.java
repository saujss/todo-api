package com.todoproject.todo.controller;

import com.todoproject.todo.Model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "v1/api/user")
@RestController
public interface UserController {

  @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<String> register(@RequestBody User user);

  @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<String> login(@RequestParam String userName, @RequestParam String password);
}
