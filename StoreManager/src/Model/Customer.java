package Model;

public class Customer {

	String name;
	String phoneNum;
	boolean wantsUpdates;
	
	public Customer(String name, String phoneNum, boolean wantsUpdates)
	{
		this.name=name;
		this.phoneNum=phoneNum;
		this.wantsUpdates=wantsUpdates;
	}

	public String getName() {
		return name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public boolean isWantsUpdates() {
		return wantsUpdates;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", phoneNum=" + phoneNum + ", wantsUpdates=" + wantsUpdates + "]";
	}

	
}
