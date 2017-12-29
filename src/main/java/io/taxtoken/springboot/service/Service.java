package io.taxtoken.springboot.service;

//import java.util.HashMap;

import io.taxtoken.springboot.dto.User;
import io.taxtoken.springboot.repository.UserRepository;

public class Service {

	public static String URL = "TaxToken.io";
//	public static HashMap<String,User> users = new HashMap<String,User>();
	public static UserRepository repo;

	public static String getUrl() {
		return URL;
	}
	
	public static String findBirthdayByName(String name) {
		if(repo.findByName(name) == null) {
			return null;
		} else {
			return repo.findByName(name).getBirthday();
		}
	}
	
	public static int findAgeByName(String name) {
		if(repo.findByName(name) == null) {
			return 0;
		} else {
			return repo.findByName(name).getAge();
		}
	}

	public static boolean addUser(User user) {
		if(repo.findByName(user.getName()) == null) {
			return false;
		} else {
			repo.save(user);
			return true;
		}
	}

	public static boolean updateUser(User user) {
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
