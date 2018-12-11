
public class CheckingAccount extends BankAccount
{
	//fields
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions;
	//constructors
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE=freeTrans;
		TRANSACTION_FEE=tf;
		FREE_TRANS=freeTrans;
		numTransactions=0;
	}
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n, 0);
		OVER_DRAFT_FEE=freeTrans;
		TRANSACTION_FEE=tf;
		FREE_TRANS=freeTrans;
		numTransactions=0;
	}
	
	//methods
	public void deposit(double amt)
	{
		if(amt<0)
			 throw new IllegalArgumentException("Deposit ammount must be positive");
		super.deposit(amt);
		if (numTransactions > FREE_TRANS)
			super.withdraw(TRANSACTION_FEE);
		if (this.getBalance() < 0)
			super.withdraw(OVER_DRAFT_FEE);
	}
	public void withdraw(double amt)
	{
		if(amt<0)
			 throw new IllegalArgumentException("Withdraw ammount must be positive");
		if(this.getBalance()<0)
			throw new IllegalArgumentException("Cannot withdraw from a negative balance");
		super.withdraw(amt);
		if (numTransactions > FREE_TRANS)
			super.withdraw(TRANSACTION_FEE);
		if (this.getBalance() < 0)
			super.withdraw(OVER_DRAFT_FEE);
	}
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
	}
	public void endOfMonthUpdate()
	{
		numTransactions =0;
	}

	
}
