package com.todoproject.todo.repositories;

import com.todoproject.todo.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
