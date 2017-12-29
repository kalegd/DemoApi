package io.taxtoken.springboot.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int age;
    private String name;
    private String birthday;

    protected User() {}

    public User(String name, String birthday,int age) {
    	this.name = name;
        this.birthday = birthday;
        this.age = age;
    }
    
    public long getId() {
    	return id;
    }
    
    public void setId(long id) {
    	this.id = id;
    }

    public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
    public String toString() {
        return String.format(
                "User[id='%d', name='%s', birthday='%s', age='%d']",
                id, name, birthday, age);
    }

}