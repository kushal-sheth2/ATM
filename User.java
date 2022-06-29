package kushal;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

	private String firstName;
	
	private String lastName;
	
	//uuid = universal unique ID
	private String uuid;
	
	//the MD5 hash of the users pin number
	private byte pinHash[];
	
	//list of accounts of this user
	private ArrayList<Account> accounts;
	
	//constructor
	public User(String firstName, String lastName, String pin, Bank theBank) {
		this.firstName = firstName;
		this.lastName = lastName;
		
		//stores the pin's MD5 rather than the original values, for security resons
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("error caught, NoSuchAlgoExist");
			e.printStackTrace();
			System.exit(1);
		}
		
		
		//get a new unique universal ID for the user
		this.uuid = theBank.getNewUserUUID();
		
		//create empty list of accounts
		this.accounts = new ArrayList<Account>();
		
		//print log message
		System.out.printf("New user %s, %s with ID %s created. \n", lastName, firstName, this.uuid);
	}
	
	
	/**
	 * add an account for a user
	 * @param anAcct - the account to add
	 */
	
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	/**
	 * Return the user's UUID
	 * @return the uuid
	 */
	public String getUUID() {
		return this.uuid;
	}
	
	/**
	 * Check whether a given pin matches the true user pin
	 * @param aPin		the pin to check
	 * @return			whether the pin is valid or not
	 */
	public boolean validatePin(String aPin) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("error caught, NoSuchAlgoExist");
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
	}
	
	/**
	 * Return's users first name
	 * @return
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	public void printAccountsSummary() {
		System.out.printf("\n\n%s's account summary\n", this.firstName);
		for(int a = 0; a < this.accounts.size(); a++) {
			System.out.printf(" %d) %s \n", a + 1, this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}
	
	/**
	 * Get the number of accounts of the user
	 * @return the number of accounts
	 */
	public int numAccounts() {
		return this.accounts.size();
	}
	
	/**
	 * Print transaction history for particular account
	 * @param acctIdx		Index of the account to use
	 */
	public void printAcctTranHistory(int acctIdx)  {
		this.accounts.get(acctIdx).printTransHistory();
	}
	
	/**
	 * Get the account balance of a particular account
	 * @param acctIdx			the index of the account to use
	 * @return					the balance of the account
	 */
	public double getAccountBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	
	/**
	 * Get the UUID of a particular account
	 * @param acctIdx		the index of the account to be used
	 * @return				the UUID of the account
	 */
	public String getAcctUUID(int acctIdx) {
			return this.accounts.get(acctIdx).getUUID();
	}
	
	/**
	 * Add a transaction to a particular account
	 * @param acctIdx	the index of an account
	 * @param amount	the amount of the transaction
	 * @param memo		the memo of the transaction
	 */
	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
}
