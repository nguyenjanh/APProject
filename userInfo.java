package onlineBanking;

public class userInfo {
	
	String username;
	String password;
	int bankID;
	double checkingBal;
	double savingsBal;
	
	public userInfo() {
		super();
		
	}
	
	public userInfo(String username, String password, int bankID, double checkingBal, double savingsBal) {
		super();
		this.username = username;
		this.password = password;
		this.bankID = bankID;
		this.checkingBal = checkingBal;
		this.savingsBal = savingsBal;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBankID() {
		return bankID;
	}

	public void setBankID(int bankID) {
		this.bankID = bankID;
	}

	public double getCheckingBal() {
		return checkingBal;
	}

	public void setCheckingBal(double d) {
		this.checkingBal = d;
	}

	public double getSavingsBal() {
		return savingsBal;
	}

	public void setSavingsBal(double savingsBal) {
		this.savingsBal = savingsBal;
	}
	
	@Override
	public String toString() {
		return "userInfo [bankID=" + bankID + ", checkingBal=" + checkingBal + ", savingsBal=" + savingsBal + ", username=" + username + ", password=" + password +"]";
	}
	
	
}
