package com.todoproject.todo.controller;

import com.todoproject.todo.Model.Status;
import com.todoproject.todo.Model.Task;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/task")
@RestController
public interface TaskController {

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<String> createTask(@RequestBody Task task);

  @PostMapping(value = "/changeStatus", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<String> changeStatus(@RequestParam int taskId, @RequestParam Status status);

  @GetMapping(value = "/getTasks/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List> getTaskListByUserId(@PathVariable String userId);

}
