/**
 * CheckingAccount
 * Rules: balance can dip below zero, but no more money can be taken out at that point
 * 		-User is given a certain amount of free transactions
 * @author Bharavi Misra
 *
 */
public class CheckingAccount extends BankAccount
{
	//fields
	/**
	 * double OVER_DRAFT_FEE	fee that is charged whenever account dips below zero
	 * double TRANSACTION_FEE	fee that is charged whenever makes a transaction after the initial free amount
	 * double FREE_TRANS	number of free transactions user is offered
	 * int numTransactions	keeps track of number of transactions
	 */
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions;
	//constructors
	/**
	 * 
	 * @param n	name of account holder
	 * @param b	starting balance
	 * @param odf	over draft fee
	 * @param tf	transaction fee
	 * @param freeTrans	number of free transactions
	 */
	public CheckingAccount(String n, double b, double odf, double tf, double freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE=odf;
		TRANSACTION_FEE=tf;
		FREE_TRANS=freeTrans;
		numTransactions=0;
	}
	/**
	 * Balance automatically set to 0
	 * @param n	name of account holder
	 * @param odf	over draft fee
	 * @param tf	transaction fee
	 * @param freeTrans	number of free transactions
	 */
	public CheckingAccount(String n, double odf, double tf, double freeTrans)
	{
		super(n, 0);
		if(odf < 0)
			throw new IllegalArgumentException("Deposit ammount must be positive");
		if(tf < 0)
			throw new IllegalArgumentException("Deposit ammount must be positive");
		if(freeTrans < 0)
			throw new IllegalArgumentException("Deposit ammount must be positive");
		OVER_DRAFT_FEE=freeTrans;
		TRANSACTION_FEE=tf;
		FREE_TRANS=freeTrans;
		numTransactions=0;
	}
	
	//methods
	/**
	 * This method deposits a certain sum of money into the account
	 * If number of transactions exceed the number of free transactions, the account is charged
	 * @param amt	amount of money to be deposited
	 */
	public void deposit(double amt)
	{
		if(amt<0)
			 throw new IllegalArgumentException("Deposit ammount must be positive");
		super.deposit(amt);
		if (numTransactions > FREE_TRANS)
			super.withdraw(TRANSACTION_FEE);
		if (this.getBalance() < 0)
			super.withdraw(OVER_DRAFT_FEE);
		numTransactions++;
	}
	/**
	 * This method withdraws a certain sum of money from the account
	 * If number of transactions exceed the number of free transactions, the account is charged
	 * @param amt	amount of money to be withdrawn
	 */
	public void withdraw(double amt)
	{
		if(amt<0)
			 throw new IllegalArgumentException("Withdraw ammount must be positive");
		if(this.getBalance() < 0)
			throw new IllegalArgumentException("Cannot withdraw from a negative balance");
		if (numTransactions >= FREE_TRANS)
			amt += TRANSACTION_FEE;
		if (this.getBalance()  - amt < 0)
			amt += OVER_DRAFT_FEE;
		super.withdraw(amt);
		numTransactions++;
	}
	/**
	 * This method transfers a certain sum of money from one account to another
	 * If number of transactions exceed the number of free transactions, the account is charged
	 * Both accounts must have the same name for a transfer
	 * @param other	BankAccount where money will be transferred to
	 * @param amt	amount of money to be transferred
	 */
	public void transfer (BankAccount other, double amt)
	{
		if (!this.getName().equals(other.getName()))
			 throw new IllegalArgumentException("Both accounts must have the same name");
		if (numTransactions > FREE_TRANS)
		{
			if (amt + TRANSACTION_FEE > this.getBalance())
				 throw new IllegalArgumentException("Cannot transfer more money than is in account");
			else super.transfer(other, amt);
		} else 
		{
			if (amt > this.getBalance())
				 throw new IllegalArgumentException("Cannot transfer more money than is in account");
			else super.transfer(other, amt);
		}
		numTransactions++;
	}
	/**
	 * resets number of transactions back to zero
	 */
	public void endOfMonthUpdate()
	{
		numTransactions =0;
	}

	
}
