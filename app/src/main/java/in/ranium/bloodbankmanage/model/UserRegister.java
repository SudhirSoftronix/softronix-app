package in.ranium.bloodbankmanage.model;

public class UserRegister {

	String ID, NAME ,PASSWORD;



	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}


	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}


	public UserRegister( String  NAME, String PASSWORD) {
		super();

		this.NAME = NAME;
		this.PASSWORD = PASSWORD;

	}

	public UserRegister() {
		super();
	}


	
}
