package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

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
	public static final int PRODUCT_KEY_SIZE = 10;

	private Map<String, Product> productsMap; // the maps string is the product id;
	private LinkedHashMap <String, Product> productsDefualtOrder;
	private Set<StoreModelListener> allListener;
	private Comparator<String> comparator;
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private StoreProductsMomento productsMomento;
	private StoreUpdates updates;
	String[] customersNames;
	private Iterator<Entry<String, Product>> it;
	int numOfProducts;
	SortType sortType;

	public Store() {
		numOfProducts = 0;
		productsMap = new LinkedHashMap<String, Product>();
		allListener = new HashSet<StoreModelListener>();
		sortType = SortType.eIncome_Order; // By default
		it = new FileIterator().getIterator(F_NAME);
		updates = StoreUpdates.getInstance();
	}

	@Override
	public int saveProductsToBinaryFile(String fileName) {

		try {
			RandomAccessFile rafOut = new RandomAccessFile(F_NAME, "rw");

			for (java.util.Map.Entry<String, Product> e : productsMap.entrySet()) {
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

	public int readProductsFromBinaryFile(String fileName) throws ClassNotFoundException {
		/*
		 * Here you need to read from binary file with an Iterator on "products.dat" In
		 * the following order: Read Int - counter for number of products Read product
		 * in a loop by the counter and put it inside the map You have to put in a
		 * specific map with a comparator by the sortType. You can use "updateMapType"
		 * function.
		 */

		try {
			RandomAccessFile rafIn = new RandomAccessFile(F_NAME, "r");
			if(rafIn.length()==0)
				return 0;
			int size = (int) (rafIn.length() / (PRODUCT_KEY_SIZE + Product.PRODUCT_SIZE));
			// System.out.println("rafIn.length = " + rafIn.length() + "size = " + size + "
			// --- " + (PRODUCT_KEY_SIZE + Product.PRODUCT_SIZE));
			for (int i = 0; i < size; i++) {
				numOfProducts++;
				String k = File_IO.readFixedString(PRODUCT_KEY_SIZE, rafIn);
				Product p = Product.readProductToFile(rafIn);
				productsMap.put(k, p);
				if(p.getCustomer().isWantsUpdates())
				{
					updates.attach(p.getCustomer());
				}
			}

			rafIn.close();
			return 1;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0; // If the are no products inside the file, if you reading is successful return 1
	}

	public void addProduct(String key, Product p) {
		/*
		 * After adding each product, you have to re- write the binary file- Updated
		 * number of products and all of the products that are inside the map. Also
		 * everything has to happen with Command pattern
		 */
		productsMomento = new StoreProductsMomento(productsMap);
		productsMap.put(key, p);
		this.numOfProducts++;
		if (p.getCustomer().wantsUpdates)
			updates.attach(p.getCustomer());
		saveProductsToBinaryFile(F_NAME);


	}

	private void startIterationOnFile() throws ProductIdNotFoundException {
		Iterator<Entry<String, Product>> it = new FileIterator().getIterator(F_NAME);
		int res = 1;
		Entry<String, Product> e;
		switch (res) {

		case 1:// reading products from file using the iterator and insert to the map;
			e = it.next();
			productsMap.put(e.getKey(), e.getValue());
			break;

		case 2: // remove product by ID
			String productID = "2001";
			while (it.hasNext()) {
				e = it.next();
				if (e.getKey().equalsIgnoreCase(productID)) {
					it.remove();
					return;
				}
			}
			throw new ProductIdNotFoundException();

		case 3: // remove all products
			while (it.hasNext()) {
				e = it.next();
				it.remove();
				removeProduct(e.getKey());
			}
		}
	}
	public int removeLastProduct() throws UnableToRecoveryLastProductException {
		/*
		 * Allow by Memento pattern to cancel the operation of adding the last product.
		 * Remove it from both map and binary file.
		 */
		if (productsMomento == null)
			throw new UnableToRecoveryLastProductException();
		productsMap = productsMomento.getProductsMap();
		productsMomento = null;
		return 1; // If product has been removed successfully
	}

	public void updateMapType(int type) {
		this.sortType = SortType.values()[type];
		System.out.println(SortType.values()[type]);
		Map<String, Product> tempMap;
		switch (this.sortType) {

		case eAscending_Order:
			/*
			 * Create a comparator that sorts the products by ascending order, set the
			 * comparator by "setComperator" function, and create a tree map with this
			 * comparator, Copy all the the products inside the current map, and update the
			 * map. Update the file with the file iterator by the new map (re - write the
			 * file).
			 */

			this.setComperator(new CompairProductByAscendingID());
			// restore all the map entities in ascending order
			TreeMap<String, Product> productsMapTemp = new TreeMap<String, Product>(comparator);
			productsMapTemp.putAll(productsMap);
			removeAllProducts();
			reWriteBinaryFileAndMapWithSortedProducts(productsMapTemp);
			productsMap=productsMapTemp;
			break;

		case eDescending_Order:
			/*
			 * Create a comparator that sorts the products by descending order, set the
			 * comparator by "setComperator" function, and create a tree map with this
			 * comparator. Copy all the the products inside the current map, and update the
			 * map. Update the file with the file iterator by the new map (re - write the
			 * file).
			 */
			this.setComperator(new CompairProductByDescendingID());
			productsMapTemp = new TreeMap<String, Product>(comparator);
			productsMapTemp.putAll(productsMap);
			removeAllProducts();
			reWriteBinaryFileAndMapWithSortedProducts(productsMapTemp);
			productsMap=productsMapTemp;
			break;

		case eIncome_Order:
			/*
			 * Create a linkedHashMap and copy all the the products inside the current map,
			 * and update the map. Update the file with the file iterator by the new map (re
			 * - write the file).
			 */
			this.setComperator(new ValueComparatorForIncomeOrder(productsMap));
			productsMapTemp = new TreeMap<String, Product>(comparator);
			productsMapTemp.putAll(productsMap);
			removeAllProducts();
			reWriteBinaryFileAndMapWithSortedProducts(productsMapTemp);
			//LinkedHashMap<String , Product> newMap = new LinkedHashMap<String, Product>();
			productsMap=  new LinkedHashMap<String, Product>(productsMap);
			System.out.println("WOOOOOOOOOOORKSSSSSSSSSSSSSSSSSSS");
			break;

		default:
			break;

		}

	}

	private void reWriteBinaryFileAndMapWithSortedProducts(TreeMap <String, Product> map) {
		for(Iterator<Entry<String, Product>> it = map.entrySet().iterator(); it.hasNext(); ) {
		    Entry<String, Product> entry = it.next();
		    addProduct(entry.getKey(),entry.getValue());
		}
		
	}

	private void setComperator(Comparator<String> comparator) {
		this.comparator = comparator;

	}

	public Product findProduct(String catalogNum) {
		if (productsMap.containsKey(catalogNum))
			return productsMap.get(catalogNum);
		return null;
	}

	public Product findProductInFile(String catalogNumber) {
		it = new FileIterator().getIterator(F_NAME);
		while (it.hasNext()) {
			Map.Entry<String, Product> e = it.next();
			if (e.getKey().equalsIgnoreCase(catalogNumber))
				return e.getValue();

		}

		return null;

	}

	public int removeProduct(String catalogNumber) {
		if(numOfProducts==0)
			return 0;
		productsMap.remove(catalogNumber);
		removeProductFromFile(catalogNumber);
		numOfProducts--;
		return 1;

		/*
		 * Removing specific product ,and also from file with File Iterator.
		 */

	}

	private void removeProductFromFile(String catalogNumber) {
		findProductInFile(catalogNumber);
		it.remove();
		
	}

	public int getNumOfProducts() {
		return numOfProducts;
	}

	public Map<String, Product> getMap() {
		return productsMap;
	}

	public int removeAllProducts() {
		/*
		 * Remove all products from map and binary file with the iterator, if there are
		 * no products to remove, return 0, if all removed return 1.
		 */
		if(numOfProducts==0)
			return 0;
		for(Iterator<Entry<String, Product>> it = productsMap.entrySet().iterator(); it.hasNext(); ) {
		    Entry<String, Product> entry = it.next();
		        it.remove();
		        removeProductFromFile(entry.getKey());
		    System.out.println(entry.getKey() + " " + entry.getValue());
		}
		return 1;
	}

	@Override
	public void registerListener(StoreController controller) {
		allListener.add(controller);
	}
	public StoreUpdates getStoreUpdates()
	{
		return updates;
	}
	public String[] getCustomersNames()
	{
		return customersNames;
	}
	public void setCustomersNames(String[] customersNames) {
		this.customersNames= customersNames;
		
	}
}
