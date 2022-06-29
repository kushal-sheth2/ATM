package kushal;

import java.util.Date;

public class Transaction {

	private double amount;
	
	//time and date of this transaction
	private Date timestamp;
	
	//a memo for this transaction
	private String memo;
	
	//account in which transaction were performed
	private Account inAccount;
	
	/**
	 * Create a new transaction
	 * @param amount - the amount transacted
	 * @param inAccount - the account transaction belong to
	 */
	//constructor
	public Transaction(double amount, Account inAccount) {
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestamp = new Date();
		this.memo = "";
	}
	
	
	/**
	 * Create a new transaction
	 * @param amount - the amount transacted
	 * @param memo - the memo for the transaction
	 * @param inAccount - the account transaction belong to
	 */
	//constructor overloading
	public Transaction(double amount, String memo, Account inAccount) {
		
		//call the two args constructor
		this(amount, inAccount);
		
		//set the memo
		this.memo = memo;
		
	}
	
	/**
	 * Get the amount of the transaction
	 * @return		the amount
	 */
	public double getAmount() {
		return this.amount;
	}
	
	/**
	 * Get a string summarizing the transaction
	 * @return the summary string
	 */
	public String getSummaryLine() {
		if(this.amount >= 0) {
			return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
		} else {
			return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), -this.amount, this.memo);
		}
	}
}
