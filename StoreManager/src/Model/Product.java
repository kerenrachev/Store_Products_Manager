package Model;

public class Product {

	private String productName;
	private double storeCostPrice;
	private double sellingPrice;
	private Customer customer;
	public Product(String productName, double storeCostPrice, double sellingPrice,Customer customer)
	{
		this.productName=productName;
		this.storeCostPrice= storeCostPrice;
		this.sellingPrice=sellingPrice;
		this.customer=customer;
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
	
	
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", storeCostPrice=" + storeCostPrice + ", sellingPrice="
				+ sellingPrice + ", customer=" + customer + "]";
	}
	
	
	
}
