package io.taxtoken.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import io.taxtoken.springboot.dto.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByName(String name);
}