package banking.interfaces;

import banking.primitive.*;
import banking.primitive.core.DepositParameter;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public abstract class Account implements Asset
{
	/*
	 * CST316 ACTIVITY 1.4
	 */
	protected float accountBlance =0.0F;
	protected String name;

	public Account(String n)
	{
		name = n;
	}

	public Account(String n, float b)
	{
		name = n;
		accountBlance = b;
	}

	public void display()
	{
	    JOptionPane.showMessageDialog(null, "Account " + name + " has $" + accountBlance);
	}

	public String getName()
	{
		return name;
	}

	public float getBalance()
	{
		return accountBlance;
	}
	
	public abstract void deposit(DepositParameter parameterObject);
	public abstract void withdraw(float amount);

    public String toString() {
    	return "Account " + name + " has $" + accountBlance +"\n";
    }
}
