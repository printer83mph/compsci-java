// THIS IS ALL WRONG. KEEP GETTERS AND SETTERS AS PUBLIC THINGS IN GLOBAL SCOPE

package bank;

import java.util.ArrayList;
import java.math.*;

public class Bank {

	private class BankAccount {
	
		private class CheckingAccount {
			
			String accountName;
			long balance;
			// balance is E-2 (no need for floating point), so 12345 is $123.45
			
			public CheckingAccount(String n) {
				accountName = n;
				balance = 0;
			}
			
			public CheckingAccount(String n, long b) {
				accountName = n;
				balance = b;
			}
			
			public String getName() {
				return accountName;
			}
			
			public long getBalance() {
				return balance;
			}
			
			public void addFunds(long x) {
				if (x <= 0) {
					System.out.println("Not a valid amount!");
				} else {
					balance += x;
					System.out.println("Funds successfully added!");
				}
			}
			
			public void removeFunds(long x) {
				if (x <= 0) {
					System.out.println("Not a valid amount!");
				} else if (balance - x < 0) {
					System.out.println("Not enough funds!");
				} else {
					balance -= x;
					System.out.println("Funds successfully removed!");
				}
			}
			
		}
		
		private String accountHolder;
		private String username;
		private String password;
		private ArrayList<CheckingAccount> accounts;
		
		public BankAccount(String n, String u, String p) {
			
			accountHolder = n;
			username = u;
			password = p;
			accounts = new ArrayList<CheckingAccount>();
			accounts.add(new CheckingAccount("Main"));
			
		}
		
		public String getUsername() {
			return username;
		}
		
		public String getAccountHolder() {
			return accountHolder;
		}
		
		public Boolean login(String user, String pass) {
			return username == user && password == pass;
		}
		
		public CheckingAccount getAccount(String n) {
			for (int i = 0; i < accounts.size(); i++) {
				if (accounts.get(i).getName() == n) {
					return accounts.get(i);
				}
			}
			throw new IllegalArgumentException("Account not found!");
		}
		
		public long getBalance(String acn) {
			return getAccount(acn).getBalance();
		}
		
		public void addFunds(long x, String acn) {
			getAccount(acn).addFunds(x);
		}
		
		public void removeFunds(long x, String acn) {
			getAccount(acn).removeFunds(x);
		}

		public void printAccounts() {
			for (int i = 0; i < accounts.size(); i++) {
				CheckingAccount account = accounts.get(i);
				System.out.println(Integer.toString(i + 1) + ". " + account.getName() + ": $" + account.getBalance()/100);
			}
		}
		
	}
	
	private String bankName;
	private ArrayList<BankAccount> bankAccounts;
	private BankAccount currentBankAccount;
	
	public Bank(String n) {
		
		bankName = n;
		bankAccounts = new ArrayList<BankAccount>();
		
	}
	
	public String getBankName() {
		return bankName;
	}
	
	public Boolean isLoggedIn() {
		return currentBankAccount != null;
	}
	
	public void login(String user, String pass) {
		
		for (int i = 0; i < bankAccounts.size(); i++) {
			BankAccount bankAccount = bankAccounts.get(i);
			if (bankAccount.login(user, pass)) {
				currentBankAccount = bankAccount;
				System.out.println("Now logged in as " + bankAccount.username + ". Welcome to " + bankName + "!");
				printBalance();
				return;
			}
		}
		System.out.println("Account not found!");
	}
	
	public void logout() {
		currentBankAccount = null;
		System.out.println("Successfully logged out.");
	}
	
	public void addAccount(String name, String user, String pass) {
		
		for (int i = 0; i < bankAccounts.size(); i++) {
			if (bankAccounts.get(i).getUsername() == user) {
				System.out.println("That username is already taken!");
				return;
			}
		}
		
		bankAccounts.add(new BankAccount(name, user, pass));
		
		System.out.println("Account created!");
		
	}

	public void printBalance(String acn) {
		if (isLoggedIn()) {
			System.out.println("$" + currentBankAccount.getBalance(acn) / 100);
		} else {
			System.out.println("Not logged in!");
		}
	}
	
	public void addFunds(double x, String acn) {
		if (isLoggedIn()) {
			currentBankAccount.addFunds((long)(x*100), acn);
		} else {
			System.out.println("Not logged in!");
		}
	}
	
	public void removeFunds(double x, String acn) {
		if (isLoggedIn()) {
			currentBankAccount.removeFunds((long)(x*100), acn);
		} else {
			System.out.println("Not logged in!");
		}
	}
	
	public void printBalance() {
		if (isLoggedIn()) {
			System.out.println("Accounts: ");
			currentBankAccount.printAccounts();
		} else {
			System.out.println("Not logged in!");
		}
	}
	
}