package com.todoproject.todo.repositories;

import com.todoproject.todo.Model.Task;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, Integer> {
   List<Task> findByUserId(String UserId);
}
