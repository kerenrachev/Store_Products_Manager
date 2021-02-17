package Model;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.print.DocFlavor.INPUT_STREAM;

import Controller.StoreController;
import Momento.StoreProductsMomento;
import fileIterator.FileIterator;
import file_iO.File_IO;
import interfaces.StoreModelListener;
import interfaces.Store_Interface;
import observer.StoreUpdates;
import storeExceptions.ProductIdNotFoundException;
import storeExceptions.UnableToRecoveryLastProductException;


public class Store implements Store_Interface {
	public static final String F_NAME = "products txt";
	public static final int PRODUCT_KEY_SIZE = 10 ; 
	
	private Map <String,Product> productsMap; // the maps string is the product id;
	private Set<StoreModelListener> allListener;
	private Comparator<String> comparator;
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private StoreProductsMomento productsMomento;
	private StoreUpdates update;
	private Iterator<Entry<String, Product>> it;
	int numOfProducts;
	SortType sortType; 
	
	public Store ()
	{
		numOfProducts=0;
		productsMap= new LinkedHashMap<String, Product>();
		allListener = new HashSet<StoreModelListener>();
		sortType = SortType.eIncome_Order; //By default
		it = new FileIterator().getIterator(F_NAME);
		update = StoreUpdates.getInstance();
	}

	@Override
	public int saveProductsToBinaryFile(String fileName) {

		try {
			RandomAccessFile rafOut = new RandomAccessFile(F_NAME, "rw");
	
			
			for(java.util.Map.Entry<String, Product> e : productsMap.entrySet())
			{
				File_IO.writeFixedString(e.getKey(), PRODUCT_KEY_SIZE, rafOut);
				e.getValue().writeProductToFile(rafOut);
			}
			rafOut.close();
			return 1;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}


	
	public int readProductsFromBinaryFile(String fileName) throws ClassNotFoundException
	{
		/* Here you need to read from binary file with an Iterator on "products.dat" In the following order: 
		 * Read Int - counter for number of products
		 * Read product in a loop by the counter and put it inside the map
		 * You have to put in a specific map with a comparator by the sortType.
		 * You can use "updateMapType" function.
		 */
		
		try {
			RandomAccessFile rafIn = new RandomAccessFile(F_NAME, "r");
			int size = (int)(rafIn.length() / (PRODUCT_KEY_SIZE + Product.PRODUCT_SIZE));
			//System.out.println("rafIn.length = " + rafIn.length() + "size = " + size + " --- " + (PRODUCT_KEY_SIZE + Product.PRODUCT_SIZE));
			for(int i =0 ; i < size ; i++)
			{
				String k = File_IO.readFixedString(PRODUCT_KEY_SIZE, rafIn);
				Product p = Product.readProductToFile(rafIn);
				productsMap.put(k ,p);
			}

			rafIn.close();
			return 1;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;   // If the are no products inside the file, if you reading is successful return 1
	}

	public void addProduct(String key,Product p)  {
		/*
		 * After adding each product, you have to re- write the binary file-
		 * Updated number of products and all of the products that are inside the map.
		 * Also everything has to happen with Command pattern
		 */
		productsMomento = new StoreProductsMomento(productsMap);
		productsMap.put(key, p);	
		this.numOfProducts++;
		if(p.getCustomer().wantsUpdates)
			update.attach(p.getCustomer());
		saveProductsToBinaryFile(F_NAME);
		try {
			startIterationOnFile();
		} catch (ProductIdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void startIterationOnFile() throws ProductIdNotFoundException{
		// iterator menu have to change to gui
		System.out.println("1) read file contact to the map");
		System.out.println("2) remove product by ID");
//		FileIterator iter = new FileIterator();
		Iterator<Entry<String, Product>> it = new FileIterator().getIterator(F_NAME);
		int res =1;
		Entry<String, Product> e ;
		switch(res)
		{
		
		case 1:// reading products from file using the iterator and insert to the map;
			e = it.next();
			productsMap.put(e.getKey() , e.getValue());
			break;

		case 2: // remove product by ID
			String productID = "2001";
			while(it.hasNext())
			{
				e = it.next();
				if (e.getKey().equalsIgnoreCase(productID)) {
					it.remove();
					return;
				}
			}
			throw new ProductIdNotFoundException();
			
			
		case 3: // remove all products
			while(it.hasNext()) {
				e = it.next();
				it.remove();
				removeProduct(e.getKey());
			}
			
		}
		

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
	
	public void updateMapType(int type)
	{
		this.sortType=SortType.values()[type];
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
	
	public void setUpdateState() {
		String[] observers = null;
		update.setState("new status" , observers);
		// create here new inner class that extends runnable (thread) and implements the last part of the project
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
		Entry<String, Product> e ;
		while(it.hasNext()) {
			e = it.next();
			it.remove();
			removeProduct(e.getKey());
		}
		return 1;
	}

	
	
	@Override
	public void registerListener(StoreController controller) {
		allListener.add(controller);
	}

	public int sendMassages() {
		
		
		// Return 0 if there are no clients that are ineterested in updates.
		return 0;
	}
}
