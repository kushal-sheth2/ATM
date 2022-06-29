package kushal;

import java.util.ArrayList;

public class Account {

	private String name;
	
	private double balance;
	
	//different from users uuid
	private String uuid;
	
	private User holder;
	
	private ArrayList<Transaction> transactions;
	
	
	//constructor
	public Account(String name, User holder, Bank theBank) {
		
		//set the account name and holder
		this.name = name;
		this.holder = holder;
		
		//get new uuid for account
		this.uuid = theBank.getNewAccountUUID();
		
		//init transactions
		this.transactions = new ArrayList<Transaction>();
		
	}
	
	/**
	 * Get the account ID
	 * @return the uuid
	 */
	public String getUUID() {
		return this.uuid;
	}
	
	/**
	 * Get the summary line for the account
	 * @return the string summary
	 */
	public String getSummaryLine() {
		
		// get the account's balance
		double balance = this.getBalance();
		
		//format the summary line, depending on whether the balance is negative
		if(this.balance >= 0) {
			return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
		} else {
			return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
		}
	}
	
	public double getBalance() {
		double balance = 0;
		for(Transaction t: this.transactions) {
			balance += t.getAmount();
		}
		
		return balance;
	}
	
	/**
	 * Print the transaction history of the account
	 */
	public void printTransHistory() {
		System.out.printf("\nTransaction history for account %s\n", this.uuid);
		for(int t = this.transactions.size() - 1; t >= 0; t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	}
	
	/**
	 * Add a new transaction in this account
	 * @param amount	the amount transacted
	 * @param memo		the transaction memo
	 */
	public void addTransaction(double amount, String memo) {
		//create a new transaction object and add it into list
		Transaction newTrans = new Transaction(amount, memo, this) ;
		this.transactions.add(newTrans);
	}
}
