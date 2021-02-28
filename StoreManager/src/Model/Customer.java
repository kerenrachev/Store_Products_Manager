package Model;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

import file_iO.File_IO;
import observer.Observer;

public class Customer extends Observer implements Serializable {

	/**
	 * 
	 */
	
	public final static int NAME_SIZE = 50;
	public final static int PHONE_SIZE = 10;

	private static final long serialVersionUID = 1L;
	public static final int CUSTOMER_SIZE = (NAME_SIZE*2) + (PHONE_SIZE*2) + 1;
	
	
	String name;
	String phoneNum;
	boolean wantsUpdates;
	
	public Customer(String name, String phoneNum, boolean wantsUpdates)
	{
		this.name=name;
		this.phoneNum=phoneNum;
		this.wantsUpdates=wantsUpdates;
	}

	public String getPhoneNum() {
		return phoneNum;
	}
	public boolean isWantsUpdates() {
		return wantsUpdates;
	}
	
	public void writeCustomerToFile(DataOutput dataOutput) throws IOException
	{
		File_IO.writeFixedString(this.name, NAME_SIZE, dataOutput);
		File_IO.writeFixedString(this.phoneNum, PHONE_SIZE, dataOutput);
		dataOutput.writeBoolean(this.wantsUpdates);
	}
	
	public static Customer readCustomerToFile(DataInput dataInput) throws IOException
	{
		String name = File_IO.readFixedString(NAME_SIZE, dataInput);
		String phone = File_IO.readFixedString(PHONE_SIZE, dataInput);	
		Boolean wantUpdate = dataInput.readBoolean(); 
		return new Customer(name , phone , wantUpdate);
	}
	
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", phoneNum=" + phoneNum + ", wantsUpdates=" + wantsUpdates + "]";
	}

	@Override
	protected String replyToMSG() {
		return this.name ;
	}

	public String getName() {
		return this.name;
	}

	
}
