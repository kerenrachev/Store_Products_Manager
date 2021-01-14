package Model;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class Store {

	Map <String,Product> productsMap;
	int numOfProducts;
	int sortType; //1-ascending, 2-descending, 3-not sorted
	private Comparator<Product> comparator;
	
	public Store ()
	{
		numOfProducts=0;
		productsMap= new LinkedHashMap<String, Product>();
		sortType=3; //By default
	}
	
	public int readProductsFromBinaryFile()
	{
		/* Here you need to read from binary file with an Iterator on "products.dat" In the following order: 
		 * Read Int - counter for number of products
		 * Read product in a loop by the counter and put it inside the map
		 * You have to put in a specific map with a comparator by the sortType.
		 * You can use "updateMapType" function.
		 */
		return 0;   // If the are no products inside the file, if you reading is successful return 1
	}

	public void addProduct(String key,Product p) {
		productsMap.put(key, p);	
		this.numOfProducts++;
		/*
		 * After adding each product, you have to re- write the binary file-
		 * Updated number of products and all of the products that are inside the map.
		 * Also everything has to happen with Command pattern
		 */
	}
	
	public int removeLastProduct()
	{
		/*
		 *  Allow by Memento pattern to cancel the operation of adding the last product.
		 *  Remove it from both map and binary file.
		 */
		return 1;  // If product has been removed successfully
	}
	
	public void updateMapType(int type)
	{
		this.sortType=type;
		
		if(sortType==1) 
		{
			/*
			 *  Create a comparator that sorts the products by ascending order, 
			 *  set the comparator by "setComperator" function, and create a tree map with this comparator,
			 *  Copy all the the products inside the current map, and update the map.
			 *  Update the file with the file iterator by the new map (re - write the file).
			 */
		}
		if(sortType==2) 
		{
			/*
			 *  Create a comparator that sorts the products by descending order, 
			 *  set the comparator by "setComperator" function, and create a tree map with this comparator.
			 *  Copy all the the products inside the current map, and update the map.
			 *  Update the file with the file iterator by the new map (re - write the file).
			 */
		}
		if(sortType==3) 
		{
			/*
			 * Create a linkedHashMap and copy all the the products inside the current map, and update the map.
			 * Update the file with the file iterator by the new map (re - write the file).
			 */
		}
	}
	
	private void setComperator(Comparator<Product> comparator) {
		this.comparator=comparator;
		
	}
	public Product fintProduct(String catalogNum)
	{
		if(productsMap.containsKey(catalogNum))
			return productsMap.get(catalogNum);
		return null;
	}

	public void remove(String catalogNumber) {
		productsMap.remove(catalogNumber);
		
		/*
		 * Removing specific product
		 * Need to also remove from file with File Iterator.
		 */
		
	}
	public int getNumOfProducts()
	{
		return numOfProducts;
	}
	public Map getMap()
	{
		return productsMap;
	}

	public int removeAllProducts() {
		/*
		 * Remove all products from map and binary file with the iterator,
		 * if there are no products to remove, return 0, if all removed return 1.
		 */
		return 1;
	}
}
