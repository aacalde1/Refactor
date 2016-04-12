package banking.interfaces;

import banking.primitive.core.DepositParameter;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public abstract class AAccount implements IAsset
{
	/*
	 * CST316 ACTIVITY 1.4
	 */
	protected static float accountBalance =0.0F;
	protected String name;

	/*
	 * TASK 2-1 SMELL WITHIN A CLASS Duplicate Code 
	 *
	 *Duplicate code was here, Deleted.
	 */
	public AAccount(String n, float b)
	{
		name = n;
		accountBalance = b;
	}

	public void display()
	{
	    JOptionPane.showMessageDialog(null, "Account " + name + " has $" + accountBalance);
	}

	public String getName()
	{
		return name;
	}

	public float getBalance()
	{
		return accountBalance;
	}
	
	public abstract void deposit(DepositParameter parameterObject);
	public abstract void withdraw(float amount);

    public String toString() {
    	return "Account " + name + " has $" + accountBalance +"\n";
    }
}
