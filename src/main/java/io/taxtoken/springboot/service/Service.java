package io.taxtoken.springboot.service;

import java.util.HashMap;

import io.taxtoken.springboot.dto.User;

public class Service {

	public static String URL = "TaxToken.io";
	public static User[] users = new User[1];
	public static HashMap<String,User> users2 = new HashMap<String,User>();
	public static int numberOfUsers = 0;

	public static String getUrl() {
		return URL;
	}
	
	public static String findBirthdayByName(String name) {
		if(users2.containsKey(name)) {
			return users2.get(name).getBirthday();
		} else {
			return null;
		}
	}
	
	public static int findAgeByName(String name) {
		if(users2.containsKey(name)) {
			return users2.get(name).getAge();
		} else {
			return 0;
		}
	}

	public static boolean addUser(User user) {
		if(users2.containsKey(user.getName())) {
			return false;
		} else {
			users2.put(user.getName(), user);
			return true;
		}
	}

	public static boolean updateUser(User user) {
		if(users2.containsKey(user.getName())) {
			User u1 = users2.get(user.getName());
			u1.setBirthday(user.getBirthday());
			u1.setAge(user.getAge());
			return true;
		} else {
			return false;
		}
	}

}
