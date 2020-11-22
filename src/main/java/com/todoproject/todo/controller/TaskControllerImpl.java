package com.todoproject.todo.controller;

import com.todoproject.todo.Model.Status;
import com.todoproject.todo.Model.Task;
import com.todoproject.todo.Util;
import com.todoproject.todo.repositories.TaskRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskControllerImpl implements TaskController {

  @Autowired
  TaskRepository taskRepository;

  @Override
  public ResponseEntity<String> createTask(Task task) {
    if (!Util.validateNullAndEmpty(task)) {
      return ResponseEntity.badRequest().body("Invalid parameters given.");
    }
    if (taskRepository.findById(task.getId()).isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Task already present with same ID");
    }
    taskRepository.save(task);
    return ResponseEntity.status(HttpStatus.CREATED).body("Task Created");
  }

  @Override
  public ResponseEntity<String> changeStatus(int taskId, Status status) {
    if (taskRepository.findById(taskId).isPresent()) {
      Task task = taskRepository.findById(taskId).get();
      if (task.getStatus().equals(status)) {
        return ResponseEntity.ok("Same status of Task is present");
      }
      task.setStatus(status);
      taskRepository.save(task);
      return ResponseEntity.ok("Task status changed to :" + status);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No task found with this ID");
  }

  @Override
  public ResponseEntity<List> getTaskListByUserId(String userId) {
    List<Task> tasks = taskRepository.findByUserId(userId);
    if (tasks.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(tasks);
  }
}
