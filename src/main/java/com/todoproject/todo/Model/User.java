package com.todoproject.todo.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
@Getter
@Setter
public class User {

  @Id
  String userId;
  String password;
  String userName;
}
