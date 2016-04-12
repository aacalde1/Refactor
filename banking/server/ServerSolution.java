package banking.server;

import java.util.*;
import java.io.*;

import banking.interfaces.AAccount;
import banking.primitive.core.*;

class ServerSolution implements IAccountServer {

	static String fileName = "accounts.ser";

	List<AAccount> accountList = new ArrayList<AAccount>();

	public ServerSolution() {
		File file = new File(fileName);
		ObjectInputStream in = null;
		try {
			if (file.exists()) {
				System.out.println("Reading from file " + fileName + "...");
				in = new ObjectInputStream(new FileInputStream(file));

				Integer sizeI = (Integer) in.readObject();
				int size = sizeI.intValue();
				//TASK 2-2 SMELL BETWEEN CLASSES poor name for int i
				//rename i to counter
				for (int counter=0; counter < size; counter++) {
					AAccount acc = (AAccount) in.readObject();
					accountList.add(acc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException iexc) {
					iexc.printStackTrace();
				}
			}
		}
	}
	public void newAccount(String type, String name, float balance) {
		AAccount acc;
		if ("Checking".equals(type)) {
			acc = new Checking(name, balance);

		} else if ("Savings".equals(type)) {
			acc = new Savings(name, balance);

		} else {
			throw new IllegalArgumentException("Bad account type:" + type);
		}
		accountList.add(acc);
	}

	public void update(AAccount account) {
		int index = findIndex(account.getName());
		if (index < 0) {
			throw new IllegalStateException("Account not found:" + account);
		}

		accountList.remove(index);
		accountList.add(account);
	}

	public AAccount getAccount(String name) {
		int index = findIndex(name);
		if (index < 0)
			return null;

		return accountList.get(index);
	}

	public List<AAccount> getAllAccounts() {
		return accountList;
	}

	public List<AAccount> getOverdrawnAccounts() {
		List<AAccount> result = new ArrayList<AAccount>();
		//TASK 2-2 SMELL BETWEEN CLASSES poor name for int i
		for (int numberlist=0; numberlist < accountList.size(); numberlist++) {
			AAccount acc = accountList.get(numberlist);
			if (acc.getBalance() < 0) {
				result.add(acc);
			}
		}

		return result;
	}

	public void shutdown() {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));

			out.writeObject(Integer.valueOf(accountList.size()));
			for (int i=0; i < accountList.size(); i++) {
				out.writeObject(accountList.get(i));
			}
		} catch (Exception e) {
			System.out.println("Could not write file:" + fileName);
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException oexc) {
					oexc.printStackTrace();
				}
			}
		}
	}

	protected int findIndex(String name) {
		
		//TASK 2-2 SMELL BETWEEN CLASSES poor name for int i
		//rename i to indexsize
		for (int indexsize=0; indexsize < accountList.size(); indexsize++) {
			AAccount acc = accountList.get(indexsize);
			if (name.equals(acc.getName())) {
				return indexsize;
			}
		}
		return -1;
	}
}
