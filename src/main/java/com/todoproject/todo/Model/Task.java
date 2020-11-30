package com.todoproject.todo.Model;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@ToString
@Getter
@Setter
public class Task {

  @Id
  @Indexed(unique=true)
  int id;
  String description;
  Status status;
  Instant createdAt;
  Instant deadlineAt;
  String userId;
}
