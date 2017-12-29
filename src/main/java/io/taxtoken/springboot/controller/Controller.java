package io.taxtoken.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.taxtoken.springboot.dto.User;
import io.taxtoken.springboot.repository.UserRepository;

@RestController
public class Controller {
	
	@Autowired
	public UserRepository repo;
	
	public String URL = "TaxToken.io";
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> returnUrl() {
		String url = getUrl();
        if (url.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>(url, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{name}/birthday", method = RequestMethod.GET)
    public ResponseEntity<?> getBirthday(@PathVariable("name") String name) {
        String birthday = findBirthdayByName(name);
        if (birthday == null) {
            return new ResponseEntity<String>("Birthday For " + name + " Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(birthday, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{name}/age", method = RequestMethod.GET)
    public ResponseEntity<?> getAge(@PathVariable("name") String name) {
        int age = findAgeByName(name);
        if (age == 0) {
            return new ResponseEntity<String>("Age For " + name + " Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(age, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> postUser(@RequestBody User user) {
		if( !(addUser(user)) ){
			return new ResponseEntity<String>("Error adding user.", HttpStatus.CONFLICT);
		}
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{name}", method = RequestMethod.PUT) 
	public @ResponseBody ResponseEntity<?> putUser(@PathVariable("name") String name, @RequestBody User user) {
		if( !(updateUser(user)) ){
			return new ResponseEntity<String>("Error updating user.", HttpStatus.CONFLICT);
		}
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET) 
	public @ResponseBody ResponseEntity<?> getUsers() {
		if(repo.count() == 0){
			return new ResponseEntity<String>("No Users Exist.", HttpStatus.NOT_FOUND);
		}
		List<User> users = (List<User>) repo.findAll();
		String allUsers = "";
		for(int i=0; i<users.size(); i++) {
			allUsers += users.get(i).toString() + "\n";
		}
	    return new ResponseEntity<String>(allUsers, HttpStatus.OK);
	}
	
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
			u1.setBirthday(user.getBirthday());
			u1.setAge(user.getAge());
			repo.save(u1);
			return true;
		}
	}
}
