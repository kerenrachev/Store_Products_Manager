package Model;

public class TableRows {

	String catalogNum;
	String productName;
	String costPrice;
	String sellPrice;
	String profit;
	String customerName;
	String customerPhone;
	String intersetd;
	
	public TableRows(String catalogNum, String productName,String costPrice,String sellPrice,String profit,String customerName,
			String customerPhone, String interested)
	{
		this.catalogNum=catalogNum;
		this.productName=productName;
		this.costPrice= costPrice;
		this.sellPrice=sellPrice;
		this.profit=profit;
		this.customerName=customerName;
		this.customerPhone=customerPhone;
		this.intersetd=interested;
	}

	public String getCatalogNum() {
		return catalogNum;
	}

	public String getProductName() {
		return productName;
	}

	public String getCostPrice() {
		return costPrice;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public String getProfit() {
		return profit;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public String getIntersetd() {
		return intersetd;
	}
	
	
}
