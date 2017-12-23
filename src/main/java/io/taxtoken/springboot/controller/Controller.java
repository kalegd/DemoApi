package io.taxtoken.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.taxtoken.springboot.dto.User;
import io.taxtoken.springboot.service.Service;

@RestController
public class Controller {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> getUrl() {
		String url = Service.getUrl();
        if (url.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>(url, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{name}/birthday", method = RequestMethod.GET)
    public ResponseEntity<?> getBirthday(@PathVariable("name") String name) {
        String birthday = Service.findBirthdayByName(name);
        if (birthday == null) {
            return new ResponseEntity<String>("Birthday For " + name + " Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(birthday, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{name}/age", method = RequestMethod.GET)
    public ResponseEntity<?> getAge(@PathVariable("name") String name) {
        int age = Service.findAgeByName(name);
        if (age == 0) {
            return new ResponseEntity<String>("Age For " + name + " Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(age, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> postUser(@RequestBody User user) {
		if( !(Service.addUser(user)) ){
			return new ResponseEntity<String>("Error adding user.", HttpStatus.CONFLICT);
		}
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{name}", method = RequestMethod.PUT) 
	public @ResponseBody ResponseEntity<?> putUser(@PathVariable("name") String name, @RequestBody User user) {
		if( !(Service.updateUser(user)) ){
			return new ResponseEntity<String>("Error updating user.", HttpStatus.CONFLICT);
		}
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
}
