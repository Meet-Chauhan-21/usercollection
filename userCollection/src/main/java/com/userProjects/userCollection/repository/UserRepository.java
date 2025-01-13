package com.userProjects.userCollection.repository;

import com.userProjects.userCollection.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {

}
