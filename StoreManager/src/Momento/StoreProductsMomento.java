package Momento;

import java.util.Map;
import java.util.TreeMap;

import Model.Product;
import interfaces.Store_Interface.SortType;

public class StoreProductsMomento {

	private Map <String,Product> productsMap; // the maps string is the product id;
	
	public StoreProductsMomento(Map<String, Product> map)
	{
		productsMap = new TreeMap<String, Product>(map);
	}

	public Map <String,Product> getProductsMap() {
		return productsMap;
	}
	
	
}
