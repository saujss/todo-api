package com.todoproject.todo.Model;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
@Getter
@Setter
public class Task {

  @Id
  int id;
  String description;
  Status status;
  Instant createdAt;
  Instant deadlineAt;
  String userId;
}
