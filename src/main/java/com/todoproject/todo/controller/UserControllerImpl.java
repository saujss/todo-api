package com.todoproject.todo.controller;

import com.todoproject.todo.Model.User;
import com.todoproject.todo.Util;
import com.todoproject.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserControllerImpl implements UserController {

  @Autowired
  UserRepository userRepository;

  @Override
  public ResponseEntity<String> register(User user) {
    if (!Util.validateUser(user)) {
      return ResponseEntity.badRequest().body("Invalid data given in user");
    }
    userRepository.save(user);
    return ResponseEntity.ok("User saved");
  }

  @Override
  public ResponseEntity<String> login(String userName, String password) {
    if (userName.isBlank() || userName.isEmpty()) {
      return ResponseEntity.badRequest().body("Provide a userName");
    }
    if (password.isBlank() || password.isEmpty()) {
      return ResponseEntity.badRequest().body("Provide a password");
    }
    String token;
    try {
      token = loginUser(userName, password);
    } catch (NotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    return token.isEmpty() ? ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid Password")
        : ResponseEntity.ok("{\"token\":" + "\"" + token + "\"" + "}");
  }

  private String loginUser(String userName, String password) throws NotFoundException {
    //token logic here
    User user =
        (userRepository.findById(userName).isPresent()) ? userRepository.findById(userName).get()
            : null;
    if (user == null) {
      throw new NotFoundException();
    }
    if (!password.equals(user.getPassword())) {
      return "";
    }
    return userName + "|" + password;
  }


}
