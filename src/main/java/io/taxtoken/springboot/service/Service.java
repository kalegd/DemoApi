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
			return repo.findByName(name).getBirthday();
		} else {
			return null;
		}
	}
	
	public static int findAgeByName(String name) {
		if(repo.findByName(name) == null) {
			return repo.findByName(name).getAge();
		} else {
			return 0;
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
			User u1 = repo.findByName(user.getName());
			u1.setId(user.getId());
			u1.setBirthday(user.getBirthday());
			u1.setAge(user.getAge());
			repo.save(u1);
			return true;
		} else {
			return false;
		}
	}

}
