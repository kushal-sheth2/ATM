package kushal;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
	
	private String name;
	
	private ArrayList<User> users;
	
	private ArrayList<Account> accounts;
	
	/**
	 * constructor, creating empty users and accounts list for bank
	 * @param name		name of bank
	 */
	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	
	
	
	/**
	 * Generate a new universally unique ID for a user
	 * @return
	 */
	public String getNewUserUUID() {
		
		String uuid;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique = false;
		
		//continue looping until we get a unique ID	
		do {
			//generate the number
			uuid = "";
			for(int c = 0; c < len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			//check to make sure uuid is unique
			nonUnique = false;
			for(User u: this.users) {
				if(uuid.compareTo(u.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while(nonUnique);
		
		
		return uuid;
		
	}

	public String getNewAccountUUID() {
		String uuid;
		Random rng = new Random();
		int len = 10;
		boolean nonUnique = false;
		
		//continue looping until we get a unique ID	
		do {
			//generate the number
			uuid = "";
			for(int c = 0; c < len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			//check to make sure uuid is unique
			nonUnique = false;
			for(Account a: this.accounts) {
				if(uuid.compareTo(a.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while(nonUnique);
		
		
		return uuid;
	}
	
	/**
	 * Add an account
	 * @param anAcct - the account to add
	 */
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	/**
	 * Create a new user for the bank
	 * @param firstName - User's first name
	 * @param lastName  - User's last name
	 * @param pin 		- User's pin number
	 * @return			- return new user object
	 */
	public User addUser(String firstName, String lastName, String pin) {
		// Create a new user object and add it into our list
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		// Create a savings account for the user and add it into User and Bank account list
		Account newAccount = new Account("Savings", newUser, this);
		//add to holder and bank lists
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
	
	
	public User userLogin(String userID, String pin) {
		
		// search through list of users
		for(User u: this.users) {
			if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		
		// If we haven't found the user or pin is invalid
		return null;
	}
	
	/**
	 * Get the name of the bank
	 * @return		the name of bank
	 */
	public String getName() {
		return this.name;
	}
}
