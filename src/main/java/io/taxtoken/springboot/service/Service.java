package io.taxtoken.springboot.service;

public class Service {

	public static String URL = "TaxToken.io";
	public static String[] names = {"chris", "gary", "noah", "test"};
	public static String[] birthdays = {"07/18/1994", "05/04/1992", "05/01/2013", "11/20/2000"};
	public static int[] ages = {23, 25, 5, 50};

	public static String findBirthdayByName(String name) {
		int id = 0;
		boolean found = false;

		for(int i = 0; i < names.length; i++) {
			if(name.equals(names[i])) {
				found = true;
				id = i;
			}
		}
		
		return (found) ? birthdays[id]: null;
	}

	public static int findAgeByName(String name) {
		int id = 0;
		boolean found = false;

		for(int i = 0; i < names.length; i++) {
			if(name.equals(names[i])) {
				found = true;
				id = i;
			}
		}
		
		return (found) ? ages[id] : 0;
	}

	public static String getUrl() {
		return URL;
	}

}
