package interfaces;

import java.util.Map;

import Controller.StoreController;
import Model.Product;
import storeExceptions.ProductIdNotFoundException;
import storeExceptions.UnableToRecoveryLastProductException;


public interface Store_Interface {
	
	enum SortType {eAscending_Order , eDescending_Order , eIncome_Order ,eNumOfEnums};

	void 	registerListener(StoreController controller);
	int 	readProductsFromBinaryFile(String fileName) throws ClassNotFoundException;
	int 	saveProductsToBinaryFile(String fileName);
	void 	addProduct(String key,Product p) throws ProductIdNotFoundException;
	int		removeLastProduct() throws UnableToRecoveryLastProductException;
	void 	updateMapType(int type);
	Product findProduct(String catalogNum);
	int		removeProduct(String catalogNumber);
	int 	getNumOfProducts();
	Map<String, Product> getMap();
	int 	removeAllProducts();

}
