package com.example.demo;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Person {

    private HashMap<String, Integer> accounts = new HashMap<>();
	private HashMap<Integer, Integer> balance = new HashMap<>();
    private int counter = 0;

    
	@GetMapping("/person")
	public String person() {
		return "boop";
	}

	@PutMapping("/add")
	public String add(@RequestParam String name) {
		if (accounts.containsKey(name)) {
			System.out.println(name + " is already registered!");
			return (name + " is already registered!");
		} else {
			accounts.put(name, counter);
			balance.put(counter, 0);
			counter++;
		}

		return "Account holder: " + name + ", Account number: " + accounts.get(name);
	}	
	
	@PutMapping("/addJointAccount")
	public String addJointAccount(@RequestParam String name1, String name2) {
		if (accounts.containsKey(name1) || accounts.containsKey(name2)) {
			System.out.println("One of them is already registered!");
			return ("One of them is already registered!");
		} else {
			accounts.put(name1, counter);
			accounts.put(name2, counter);
			balance.put(counter, 0);
			counter++;
		}

		return "Account holders: " + name1 + " and " + name2 + ", Account number: " + accounts.get(name1);
	}

	@PutMapping("/loadMoney")
	public void loadMoney(@RequestParam String name, int amountLoaded) {
		if (accounts.containsKey(name)) {
			int accountNumber = accounts.get(name);
			int currentBalance = balance.get(accountNumber);

			balance.put(accountNumber, currentBalance + amountLoaded);

		} else {
			System.out.println(name + " is not registered!");
		}
	}	
	
	@PutMapping("/removeMoney")
	public void removeMoney(@RequestParam String name, int amountRemoved) {
		if (accounts.containsKey(name)) {
			int accountNumber = accounts.get(name);
			if (balance.get(accountNumber) >= amountRemoved) {
				balance.put(accountNumber, (balance.get(accountNumber) - amountRemoved));
			} else {
				System.out.println("Insufficient balance!");
			}

		} else {
			System.out.println(name + " is not registered!");
		}
	}

	@GetMapping("/accountAmount")
	public int accountAmount() {
		return(counter);
	}

	@GetMapping("/getBalance")
	public String getBalance(@RequestParam String name) {
		if (accounts.containsKey(name)) {
			return("Account holder: " + name + ", Account number: " + accounts.get(name) + ", Balance: $" + balance.get(accounts.get(name)));
		} else {
			System.out.println(name + " is not registered!");
			return (name + " is not registered!");
		}
		
	}
}
