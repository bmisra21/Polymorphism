/**
 * 
 * @author Bharavi Misra
 *
 */
public abstract class BankAccount 
{
	//fields
	/**
	 * int nextAcctNum	used to make a new account number whenever a new account is made
	 * String name	name of account holder
	 * int acctNum	account number
	 * double balance	account balance
	 */
	private static int nextAcctNum = 0;
	private String name;
	private int acctNum;
	private double balance;
	
	//constructors
	/**
	 * 
	 * @param name	name of account holder
	 */
	public BankAccount(String name)
	{
		this.name = name;
		nextAcctNum++;
		acctNum= nextAcctNum;
	}
	/**
	 * 
	 * @param name	name of account holder
	 * @param balance	starting balance
	 */
	public BankAccount(String name, double balance)
	{
		this.name = name;
		this.balance = balance;
		nextAcctNum++;
		acctNum= nextAcctNum;
	}
	
	
	//methods
	/**
	 * This method deposits a certain sum of money into the account
	 * @param amt	amount of money to be deposited
	 */
	public void deposit(double amt)
	{
		if (amt<0)
			 throw new IllegalArgumentException("Deposit ammount must be positive");
		balance +=  amt;
	}
	/**
	 * This method withdraws a certain sum of money from the account
	 * @param amt	amount of money to be withdrawn
	 */
	public void withdraw(double amt)
	{
		if (amt<0)
			 throw new IllegalArgumentException("Deposit ammount must be positive");
		balance -=  amt;
	}
	/**
	 * This method returns the name of the account holder
	 * @return name of account holder
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * This method returns the current balance
	 * @return current balance of account
	 */
	public double getBalance()
	{
		return balance;
	}
	/**
	 * This method transfers a certain sum of money from one account to another
	 * @param other	BankAccount where money will be transferred to
	 * @param amt	amount of money to be transferred
	 */
	public void transfer(BankAccount other, double amt)
	{
		balance -= amt;
		other.deposit(amt);
	}
	/**This method formats how account information is returned
	 * @return a string in format: "(Account Number)	(Holder Name)	$(Balance)"
	 */
	public String toString()
	{
		return "" + acctNum + "\t" + name + "\t$" + balance;
	}
	/**
	 * This method returns the account number
	 * @return	account number
	 */
	public int getAccountNumber()
	{
		return acctNum;
	}
	/**
	 * This method refreshes an accounts specific fields, depending whether it is a checking/savings account
	 */
	public abstract void endOfMonthUpdate();
	

}
