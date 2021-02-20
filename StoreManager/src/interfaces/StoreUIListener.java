package interfaces;

import Model.Product;
import javafx.scene.control.TextField;

public interface StoreUIListener {


	void fireSearchForCatalogeNumber(String strCatalogNumber);

	void fireRemoveProductByCatalogeNumber(String catalogNumber);

	void fireUpdateMapType(int mapType);

	int firereadProductsFromBinaryFile(String F_NAME);

	void fireAddProduct(String catalogNumber, Product p,boolean determineTimeStamp);

	int fireremoveLastProduct();

	int fireSendUpdateMassages(String massage);

	void fireShowAllCustomersWhoRecievedMSG();
}