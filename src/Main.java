/**
 * @author Bharavi Misra
 * Main Method
 */
import java.util.Scanner;
import java.util.ArrayList;
public class Main 

{

	public static void main(String[] args) 
	{
		final double OVER_DRAFT_FEE = 15;
		final double RATE = .0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10;
		final double FREE_TRANSACTIONS = 10;
		Scanner in = new Scanner(System.in);
		ArrayList<BankAccount> accounts = new ArrayList();
		boolean run = true;
		while (run)
		{
			System.out.println("========================================");
			System.out.println("Would you like to: \nAdd Account \nMake a Transaction \nTerminate Program \n(Type Answer Include Spaces)");
			System.out.println("========================================");
			String add = "add account";
			String trans = "make a transaction";
			String terminate = "terminate program";
			String response = in.nextLine();
			while (!response.equalsIgnoreCase(add) && !response.equalsIgnoreCase(trans) && !response.equalsIgnoreCase(terminate))
			{
				System.out.println("Invalid response - please type again");
				response =in.nextLine();
			}
			if (response.equalsIgnoreCase(terminate))
			{
				System.out.println("Goodbye!");
				run=false;
			}
				
			else if (response.equalsIgnoreCase(add))
			{
				System.out.println("What type of account should be made? Type 'S' for savings or 'C' for checking: ");
				String response1 = ""+in.next();
				while(!response1.toLowerCase().equals("s") && !response1.toLowerCase().equals("c"))
				{
					System.out.println("Invalid response - please type again");
					response1 ="" + 
					in.nextLine();
				}
				System.out.print("Enter the name the account is under: ");
				String name = in.next();
				System.out.print("Enter the starting balance of the account: ");
				double bal = in.nextDouble();
				if(response1.toLowerCase().equals("s"))
				{
					SavingsAccount account= new SavingsAccount(name, bal, RATE, MIN_BAL, MIN_BAL_FEE);
					accounts.add(account);
				}
				if(response1.toLowerCase().equals("c"))
				{
					CheckingAccount account= new CheckingAccount(name, bal, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS);
					accounts.add(account);
				}
				System.out.println("A new account has been made");
					
			} else if(response.equalsIgnoreCase(trans))
			{
				if (accounts.isEmpty())
				{
					System.out.println("Cannot make transaction-you have no accounts");
				}
				else
				{
					System.out.print("Type 'A' to enter your account number, or 'B' get account numbers under your name: ");
					response = in.next().toLowerCase();
					int accnum = 0;
					
					switch(response)
					{
					case "a":
						System.out.print("Enter the account number: ");
						accnum= in.nextInt();
						boolean exists = false;
						for (BankAccount acc : accounts)
						{
							if (acc.getAccountNumber() == accnum)
								exists = true;
						}
						while (!exists)
						{
							System.out.print("Account not found. Type 'A' to reenter or 'B' to find accounts under your name: ");
							response =  in.next();
							if(response.toLowerCase().equals("a"))
							{
								System.out.print("Enter the account number: ");
								accnum= in.nextInt();
								for (BankAccount acc : accounts)
								{
									if (acc.getAccountNumber() == accnum)
										exists = true;
								}
							} else if (response.toLowerCase().equals("b"))
							{
								System.out.print("What name is the account under?");
								String name = in.next();
								for (BankAccount acc: accounts)
								{
									if (acc.getName().equals(name))
									{
										if (acc instanceof SavingsAccount)
											System.out.print("Type: Savings Account\t Account Number: " + acc.getAccountNumber());
										else if (acc instanceof CheckingAccount)
											System.out.print("Type: Checking Account\n Account Number: " + acc.getAccountNumber());
									}
								}
							}
						}
						break;
					
					case "b":
						System.out.print("What name is the account under?");
						String name = in.next();
						for (BankAccount acc: accounts)
						{
							if (acc.getName().equals(name))
							{
								if (acc instanceof SavingsAccount)
									System.out.print("Type: Savings Account\t Account Number: " + acc.getAccountNumber());
								else if (acc instanceof CheckingAccount)
									System.out.print("Type: Checking Account:\t Account Number: " + acc.getAccountNumber());
							}
						}
						exists = false;
						while (!exists)
						{
							System.out.print("\nEnter the account number: ");
							accnum= in.nextInt();
							for (BankAccount acc : accounts)
							{
								if (acc.getAccountNumber() == accnum)
									exists = true;
							}
						}
						
						break;
					}
					System.out.print("\nType 'D' to deposit, 'W' to withdraw, 'T'  to transfer: ");
					response =  in.next().toLowerCase();
					double balance=0;
					double balance2=0;
					switch (response)
					{
					case "d":
						System.out.print("Enter the amount you want to deposit: ");
						int amt = in.nextInt();
						try
						{
							for (BankAccount acc : accounts)
							{
								if (acc.getAccountNumber() == accnum)
								{
									acc.deposit(amt);
									balance = acc.getBalance();
								}
									
							}
						}
						catch(IllegalArgumentException a)
						{
							System.out.print("transaction not authorized");
							break;
						}
						System.out.println("Deposit of $" + amt + " made. Current account balance: $" + balance);
						break;
					case "w":
						System.out.print("Enter the amount you want to withdraw: ");
						amt = in.nextInt();
						try
						{
							for (BankAccount acc : accounts)
							{
								if (acc.getAccountNumber() == accnum)
								{
									acc.withdraw(amt);
									balance = acc.getBalance();
								}
									
							}
						}
						catch(IllegalArgumentException a)
						{
							System.out.print("transaction not authorized");
							break;
						}
						System.out.println("Withdraw of $" + amt + " made. Current account balance: $" + balance);
						break;
					case "t" :
						if (accounts.size() < 2)
						{
							System.out.println("Cannot transfer - You dont have 2 accounts");
						} else
						{
							System.out.print("Enter the acount number of the account you need to transfer to: ");
							int accnum2 = in.nextInt();
							boolean exists = false;
							for (BankAccount acc : accounts)
							{
								if (acc.getAccountNumber() == accnum2)
									exists = true;
							}
							while (!exists)
							{
								System.out.print("Re-enter the account number: ");
								accnum2= in.nextInt();
								for (BankAccount acc : accounts)
								{
									if (acc.getAccountNumber() == accnum2)
										exists = true;
								}
							}
							System.out.print("Enter the amount you want to transfer: ");
							amt = in.nextInt();
							try
							{
								for (BankAccount acc : accounts)
								{
									if (acc.getAccountNumber() == accnum)
									{
										for (BankAccount acc2 :  accounts)
										{
											if (acc2.getAccountNumber() == accnum2)
											{
												acc.transfer(acc2, amt);
												balance = acc.getBalance();
												balance2 = acc2.getBalance();
												
											}
										}
									}
								}
							}
							catch(IllegalArgumentException a)
							{
								System.out.print("transaction not authorized\n");
								break;
							}
							System.out.println("Transfer of $" + amt + " made. Balance of account 1 is $" + balance + ". Balance of account 2 is $" + balance2);
						}
						break;

					}
				}

			}
		}


	}

}
