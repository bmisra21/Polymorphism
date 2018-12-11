/**
 * 
 * @author Bharavi Misra
 *
 */
public abstract class BankAccount 
{
	//fields
	private static int nextAcctNum = 1;
	private String name;
	private int acctNum;
	private double balance;
	
	//constructors
	public BankAccount(String name)
	{
		this.name = name;
		nextAcctNum++;
	}
	public BankAccount(String name, double balance)
	{
		this.name = name;
		this.balance = balance;
		nextAcctNum++;
	}
	
	
	//methods
	public void deposit(double amt)
	{
		balance +=  amt;
	}
	public void withdraw(double amt)
	{
		balance -=  amt;
	}
	public String getName()
	{
		return name;
	}
	public double getBalance()
	{
		return balance;
	}
	public void transfer(BankAccount other, double amt)
	{
		balance -= amt;
		other.deposit(amt);
	}
	public String toString()
	{
		return "" + acctNum + "\t" + name + "\t$" + balance;
	}
	public abstract void endOfMonthUpdate();
	

}
