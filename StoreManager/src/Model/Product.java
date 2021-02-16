package Model;

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
	public static final int PRODUCT_SIZE = (NAME_SIZE*2) + Customer.CUSTOMER_SIZE + 4 + 4;

	
	
	private String productName;
	private int storeCostPrice;
	private int sellingPrice;
	private Customer customer;
	
	
	public Product(String productName, int costPrice, int sellingPrice2,Customer customer)
	{
		this.productName=productName;
		this.storeCostPrice= costPrice;
		this.sellingPrice=sellingPrice2;
		this.customer=customer;
	}
	public String getProductName() {
		return productName;
	}
	public int getStoreCostPrice() {
		return storeCostPrice;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public Customer getCustomer() {
		return customer;
	}
	
	
	public void writeProductToFile(DataOutput dataOutput) throws IOException
	{
		File_IO.writeFixedString(productName, NAME_SIZE, dataOutput);
		dataOutput.writeInt(storeCostPrice);
		dataOutput.writeInt(sellingPrice);
		customer.writeCustomerToFile(dataOutput);
	}
	
	public static Product readProductToFile(DataInput dataInput) throws IOException
	{
		String name = File_IO.readFixedString(NAME_SIZE, dataInput);
		int selling = dataInput.readInt();
		int buying = dataInput.readInt();
		Customer c = Customer.readCustomerToFile(dataInput);
		return new Product(name, buying, selling, c);
	}
	
	
	
	
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", storeCostPrice=" + storeCostPrice + ", sellingPrice="
				+ sellingPrice + ", customer=" + customer + "]";
	}
	
	
	
}
