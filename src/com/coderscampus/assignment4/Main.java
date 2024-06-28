package com.coderscampus.assignment4;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

	}

	public students[] readFile() {
		
		try (BufferedReader filereader = new BufferedReader(new FileReader("data.txt"))) {
			String line;
			int i = 0;
			
			while ((line = filereader.readLine()) != null) {

				String[] userData = line.split(",");
				User user = createUser(userData[0], userData[1], userData[2]);

				users[i] = user;
				i++;
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		finally {
		}
		
		return users;
	}

	public User createUser(String username, String password, String name) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);

		return user;
	}

	public User validateUser(User[] users, String username, String password) {
		
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {

				return user;
			}
		}
		
		return null;

	}

	public void userLogin() {
		
		UserService userService = new UserService();
		User[] users = userService.readFile();
		Scanner scanner = new Scanner(System.in);
		User user = null;
		
		int loginAttempts = 0;
		
		while (loginAttempts < 5) {
			System.out.println("Enter your username: ");
			String username = scanner.nextLine();
			
			System.out.println("Enter your password: ");
			String password = scanner.nextLine();
			
			user = userService.validateUser(users, username, password);

			loginAttempts++;
			
			if (user != null) {
				System.out.println("Welcome: " + user.getName());
				break;
			} 
			else if (loginAttempts <5 && user ==null){
				System.out.println("Invalid login, please try again");
			}
		}
		
		if (loginAttempts == 5 && user == null) {
			System.out.println("Too many failed login attempts, you are now locked out.");
		}

		scanner.close();
	
	
}
