package in.ranium.bloodbankmanage.model;

public class Donor {

	String id;
	String user_id;
	String name;
	String bloodgroup;
	String mobile;
	String address;

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}

	String isSelected;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public Donor( String name,  String bloodgroup, String mobile, String address) {
		super();
		this.name = name;
		this.bloodgroup = bloodgroup;
		this.mobile = mobile;
		this.address = address;

	}

	public Donor() {
		super();
	}


	
}
