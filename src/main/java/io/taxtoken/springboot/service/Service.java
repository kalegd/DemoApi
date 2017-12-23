package io.taxtoken.springboot.service;

import io.taxtoken.springboot.dto.User;

public class Service {

	public static String URL = "TaxToken.io";
	public static User[] users = new User[1];
	public static int numberOfUsers = 0;

	public static String getUrl() {
		return URL;
	}
	
	public static int findIdByName(String name) {
		int id = -1;

		for(int i = 0; i < numberOfUsers; i++) {
			if(name.equals(users[i].getName())) {
				id = i;
			}
		}
		
		return id;
	}
	
	public static String findBirthdayByName(String name) {
		int id = 0;
		boolean found = false;

		for(int i = 0; i < numberOfUsers; i++) {
			if(name.equals(users[i].getName())) {
				found = true;
				id = i;
			}
		}
		
		return (found) ? users[id].getBirthday() : null;
	}
	
	public static int findAgeByName(String name) {
		int id = 0;
		boolean found = false;

		for(int i = 0; i < numberOfUsers; i++) {
			if(name.equals(users[i].getName())) {
				found = true;
				id = i;
			}
		}
		
		return (found) ? users[id].getAge() : 0;
	}

	public static boolean addUser(User user) {
		
		for(int i = 0; i < numberOfUsers; i++) {
			if(user.getName().equals(users[i].getName())) {
				return false;
			}
		}
		
		if(users.length == numberOfUsers) {
			User[] tempUsers = new User[users.length * 2];
			for(int i = 0; i < users.length; i++) {
				tempUsers[i] = users[i];
			}
			users = new User[tempUsers.length];
			users = tempUsers;
		}
		
		users[numberOfUsers] = user;
		numberOfUsers++;
		return true;
	}

	public static boolean updateUser(User user) {
		int id = 0;
		boolean found = false;

		for(int i = 0; i < numberOfUsers; i++) {
			if(user.getName().equals(users[i].getName())) {
				found = true;
				id = i;
			}
		}
		if(!(found))
			return false;
		
		users[id].setName(user.getName());
		users[id].setBirthday(user.getBirthday());
		users[id].setAge(user.getAge());
		numberOfUsers++;
		return true;
		
	}

}
