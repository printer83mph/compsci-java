package bank;

public class BankAccountTest {
	
	public static void main(String[] args) {
		Bank bank = new Bank("Mafia Bank");
		bank.addAccount("Jim Arboncle", "jarboncle", "ihatemycatsomuch");
		bank.addAccount("Jon Arbuckle", "jarbuckle", "iaminlovewithmycat");
		bank.login("jarboncle", "ihatemycatsomuch");
		bank.addFunds(100, "Main");
		// TODO : checking account management
		bank.printBalance();
		bank.logout();
	}
	
}
