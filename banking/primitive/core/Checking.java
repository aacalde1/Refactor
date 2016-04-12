package banking.primitive.core;

import banking.interfaces.AAccount;

@SuppressWarnings("serial")
public class Checking extends AAccount {
	private int numWithdraws = 0;

	
	/*
	 * TASK 2-1 SMELL WITHIN A CLASS Duplicate Code 
	 *
	 *Duplicate code was here, Deleted.
	 */
	
	public Checking(String name, float balance) {
		super(name, balance);
	}

    public static Checking createChecking(String name) {
        return new Checking(name, accountBalance);
    }

	

	public void display() {
		System.out.print("Checking ");
		super.display();
	}

	public void deposit(DepositParameter parameterObject) {
		accountBalance = accountBalance + parameterObject.amount;
	}

	public void withdraw(float amount) {
		accountBalance = accountBalance - amount;
		numWithdraws++;
		if (numWithdraws > 10)
			accountBalance = accountBalance - 2;
	}

	public String toString() {
		return "Chk:" + getName() + ":" + getBalance();
	}

    public char charAt(int i) {
        return name.charAt(i);
    }
}
