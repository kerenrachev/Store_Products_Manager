package Model;

public class Product {

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
	
	
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", storeCostPrice=" + storeCostPrice + ", sellingPrice="
				+ sellingPrice + ", customer=" + customer + "]";
	}
	
	
	
}
