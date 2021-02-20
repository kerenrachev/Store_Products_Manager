package Model;

import java.util.Calendar;
import java.util.Date;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;


import file_iO.File_IO;

public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static int NAME_SIZE = 50;
	public static final int PRODUCT_SIZE = (NAME_SIZE*2) + Customer.CUSTOMER_SIZE + 8 + 8 + 8 ; // 8 bytes for each price + 8 byte for long
	
	private String productName;
	private double storeCostPrice;
	private double sellingPrice;
	private long epochTime;
	private Customer customer;
	
	
	public Product(String productName, double costPrice, double sellingPrice2,Customer customer,long epochTime2)
	{
		this.productName=productName;
		this.storeCostPrice= costPrice;
		this.sellingPrice=sellingPrice2;
		this.customer=customer;
		this.epochTime= epochTime2;
		// getTime() returns the num of millseconds since January first 1970
		System.out.println(this);
	}
	public long getTimeStamp()
	{
		return epochTime;
	}
	public void setTimeStamp (int time)
	{
		this.epochTime = time ;
	}
	public String getProductName() {
		return productName;
	}
	public double getStoreCostPrice() {
		return storeCostPrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public Customer getCustomer() {
		return customer;
	}
	
	
	public void writeProductToFile(DataOutput dataOutput) throws IOException
	{
		File_IO.writeFixedString(productName, NAME_SIZE, dataOutput);
		dataOutput.writeDouble(storeCostPrice);
		dataOutput.writeDouble(sellingPrice);
		dataOutput.writeLong(epochTime);
		customer.writeCustomerToFile(dataOutput);
	}
	
	public static Product readProductToFile(DataInput dataInput) throws IOException
	{
		String name = File_IO.readFixedString(NAME_SIZE, dataInput);
		double buying = dataInput.readDouble();
		double selling = dataInput.readDouble();
		long epochTime = dataInput.readLong();
		Customer c = Customer.readCustomerToFile(dataInput);
		return new Product(name, buying, selling, c,epochTime);
	}
	
	
	
	
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", storeCostPrice=" + storeCostPrice + ", sellingPrice="
				+ sellingPrice + ", customer=" + customer + "epochTime : "+ this.epochTime+"]";
	}
	
	
	
}
