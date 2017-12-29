package io.taxtoken.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.HashMap;

import io.taxtoken.springboot.dto.User;
import io.taxtoken.springboot.repository.UserRepository;

public class Service {

	public String URL = "TaxToken.io";
//	public static HashMap<String,User> users = new HashMap<String,User>();
	
	@Autowired
	public UserRepository repo;

	public String getUrl() {
		return URL;
	}
	
	public String findBirthdayByName(String name) {
		if(repo.findByName(name) == null) {
			return null;
		} else {
			return repo.findByName(name).getBirthday();
		}
	}
	
	public int findAgeByName(String name) {
		if(repo.findByName(name) == null) {
			return 0;
		} else {
			return repo.findByName(name).getAge();
		}
	}

	public boolean addUser(User user) {
		if(repo.findByName(user.getName()) == null) {
			repo.save(user);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateUser(User user) {
		if(repo.findByName(user.getName()) == null) {
			return false;
		} else {
			User u1 = repo.findByName(user.getName());
			u1.setId(user.getId());
			u1.setBirthday(user.getBirthday());
			u1.setAge(user.getAge());
			repo.save(u1);
			return true;
		}
	}

}
