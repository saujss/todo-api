package com.todoproject.todo;

import com.todoproject.todo.Model.Status;
import com.todoproject.todo.Model.Task;

public class Util {

  public static boolean validateNullAndEmpty(Task task) {
    return task.getId() >= 0 && (task.getStatus().equals(Status.ACTIVE) || task.getStatus()
        .equals(Status.COMPLETED)) && !task.getDescription().isEmpty() && !task.getDescription()
        .isBlank() && !task.getUserId().isBlank() && !task.getUserId().isEmpty();
  }
}
