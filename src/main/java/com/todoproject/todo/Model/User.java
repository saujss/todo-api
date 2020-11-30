package com.todoproject.todo.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@ToString
@Getter
@Setter
public class User {

  @Id
  @Indexed(unique=true)
  String userId;
  String password;
  String userName;
}
