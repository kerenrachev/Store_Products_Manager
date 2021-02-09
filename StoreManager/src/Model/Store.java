package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import Controller.StoreController;
import Momento.StoreProductsMomento;
import interfaces.StoreModelListener;
import interfaces.Store_Interface;
import storeExceptions.UnableToRecoveryLastProductException;


public class Store implements Store_Interface {
	public static final String F_NAME = "products txt";

	
	private Map <String,Product> productsMap; // the maps string is the product id;
	private Set<StoreModelListener> allListener;
	private Comparator<String> comparator;
	private StoreProductsMomento productsMomento;
	int numOfProducts;
	SortType sortType; 
	
	public Store ()
	{
		numOfProducts=0;
		productsMap= new LinkedHashMap<String, Product>();
		allListener = new TreeSet<StoreModelListener>();
		sortType = SortType.eIncome_Order; //By default
		
	}
	
	public int readProductsFromBinaryFile(String fileName)
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
		/*
		 * After adding each product, you have to re- write the binary file-
		 * Updated number of products and all of the products that are inside the map.
		 * Also everything has to happen with Command pattern
		 */
		productsMomento = new StoreProductsMomento(productsMap);
		productsMap.put(key, p);	
		this.numOfProducts++;
		saveProductsToBinaryFile(F_NAME);
		startIterationOnFile();

	}
	
	private void startIterationOnFile() {
		
//		class ConcreteIterator implements Iterator<Integer> {
//			private int cur = 0; // the index of element that 'next' will return
//			private int last = -1; // the index of the element to be removed
//
//			@Override
//			public boolean hasNext() {
//				return cur < productsMap.size();
//			}
//
//			@Override
//			public Integer next() {
//				if (!hasNext())
//					throw new NoSuchElementException();
//				int tmp = a[cur];
//				last = cur;
//				cur++;
//				return tmp;
//			}
//
//			@Override
//			public void remove() {
//				if (last == -1)
//					throw new IllegalStateException();
//				for (int i = last; i < size - 1; i++)
//					a[i] = a[i + 1];
//				a = Arrays.copyOf(a, --size);
//				cur = last;
//				last = -1;
//			}
//
//		}		
	}

	public int removeLastProduct() throws UnableToRecoveryLastProductException{
		/*
		 *  Allow by Memento pattern to cancel the operation of adding the last product.
		 *  Remove it from both map and binary file.
		 */
		if(productsMomento== null)
			throw new UnableToRecoveryLastProductException();
		productsMap = productsMomento.getProductsMap();
		productsMomento =null;
		return 1;  // If product has been removed successfully
	}
	
	public void updateMapType(SortType type)
	{
		this.sortType=type;
		Map<String, Product> tempMap;
		switch(this.sortType) {
		
		case eAscending_Order:
			/*
			 *  Create a comparator that sorts the products by ascending order, 
			 *  set the comparator by "setComperator" function, and create a tree map with this comparator,
			 *  Copy all the the products inside the current map, and update the map.
			 *  Update the file with the file iterator by the new map (re - write the file).
			 */
			
			this.setComperator(new CompairProductByAscendingID());
			// restore all the map entities in ascending order
			productsMap = new TreeMap<String, Product>(productsMap);
			break;
		
		case eDescending_Order:
			/*
			 *  Create a comparator that sorts the products by descending order, 
			 *  set the comparator by "setComperator" function, and create a tree map with this comparator.
			 *  Copy all the the products inside the current map, and update the map.
			 *  Update the file with the file iterator by the new map (re - write the file).
			 */
			this.setComperator(new CompairProductByDescendingID());
			tempMap = new TreeMap<String, Product>(Collections.reverseOrder());
			tempMap.putAll(productsMap);
			productsMap =tempMap;
			break;
		
		
		case eIncome_Order:
			/*
			 * Create a linkedHashMap and copy all the the products inside the current map, and update the map.
			 * Update the file with the file iterator by the new map (re - write the file).
			 */
			this.setComperator(null);
			tempMap = new TreeMap<String, Product>();
			tempMap.putAll(productsMap);
			productsMap =tempMap;
			break;
			
		default:
			break;

		}	

	}
	
	private void setComperator(Comparator<String> comparator) {
		this.comparator=comparator;
		
	}
	
	public Product findProduct(String catalogNum){
		if(productsMap.containsKey(catalogNum))
			return productsMap.get(catalogNum);
		return null;
	}

	public int removeProduct(String catalogNumber) {
		productsMap.remove(catalogNumber);
		return 1;
		
		/*
		 * Removing specific product
		 * Need to also remove from file with File Iterator.
		 */
		
	}
	
	public int getNumOfProducts(){
		return numOfProducts;
	}
	
	public Map<String, Product>getMap()
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

	@Override
	public int saveProductsToBinaryFile(String fileName) {

		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(F_NAME));
			o.writeObject(productsMap.size());
			for(java.util.Map.Entry<String, Product> e : productsMap.entrySet())
			{
				o.writeObject(e.getKey());
				o.writeObject(e.getValue());
			}
			o.close();
			return 1;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void registerListener(StoreController controller) {
		// TODO Auto-generated method stub
		allListener.add(controller);
	}
}
