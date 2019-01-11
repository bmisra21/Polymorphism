/**
 * SavingsAccount
 * Rules: Balance cannot go below zero. If a certain balance is not maintained, the user is charged.
 * @author Bharavi Misra
 *
 */
public class SavingsAccount extends BankAccount
{
	//fields
	/**
	 * double intRate	interest rate
	 * double MIN_BAL	minimum balance user must maintain
	 * double MIN_BAL_FEE	if user dips below minimum balance, they are charged the min bal fee
	 */
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	//constructors
	/**
	 * @param b	starting balance
	 * @param n name of account holder
	 * @param r	interest rate
	 * @param mb	minimum balance of account
	 * @param mbf	minimum balance fee
	 */
	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		intRate=r;
		MIN_BAL=mb;
		MIN_BAL_FEE=mbf;
	}
	/**
	 * Balance automatically set to 0
	 * @param n name of account holder
	 * @param b starting balance
	 * @param r	interest rate
	 * @param mb	minimum balance of account
	 * @param mbf	minimum balance fee
	 */
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n, 0);
		intRate=r;
		MIN_BAL=mb;
		MIN_BAL_FEE=mbf;
	}
	
	//methods
	/**
	 * This method withdraws a certain sum of money from the account
	 * If the balance dips below the minimum balance, the account is charged
	 * @param amt	amount of money to be withdrawn
	 */
	public void withdraw(double amt)
	{
		if (amt <  0)
			throw new IllegalArgumentException("Error: cannot withdraw neg amoount");
		if (this.getBalance() - amt < 0)
			throw new IllegalArgumentException("Error: balance cannot go negative");
		if (this.getBalance() - amt < MIN_BAL)
			super.withdraw(amt + MIN_BAL_FEE);
		else super.withdraw(amt);
	}
	/**
	 * This method transfers a certain sum of money from one account to another
	 * If the balance dips below the minimum balance, the account is charged
	 * Both accounts must have the same name for a transfer
	 * @param other	BankAccount where money will be transferred to
	 * @param amt	amount of money to be transferred
	 */
	public void transfer(BankAccount other, double amt)
	{
		if (!this.getName().equals(other.getName()))
			 throw new IllegalArgumentException("Both accounts must have the same name");
		if (this.getBalance() < amt)
			throw new IllegalArgumentException("Error: balance cannot go negative");
		if (this.getBalance() < MIN_BAL)
		{
			if (this.getBalance()  < amt + MIN_BAL_FEE)
				throw new IllegalArgumentException("Error: balance cannot go negative");
		}
		if (other instanceof CheckingAccount)
		{
			this.withdraw(amt);
			other.deposit(amt);
		}
			
	}
	/**
	 * calculates a new balance by adding interest
	 */
	public void addInterest()
	{
		this.deposit(getBalance()*intRate);
	}
	/**
	 * calculates a new balance by adding interest
	 */
	public void endOfMonthUpdate()
	{
		this.addInterest();
	}
	
	
	
	
	
}
