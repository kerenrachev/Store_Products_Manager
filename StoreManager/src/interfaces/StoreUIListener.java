package interfaces;

import Model.Customer;
import Model.Product;
import javafx.scene.control.TextField;

public interface StoreUIListener {


	void fireSearchForCatalogeNumber(String strCatalogNumber);

	void fireRemoveProductByCatalogeNumber(String catalogNumber);

	void fireUpdateMapType(int mapType);

	int firereadProductsFromBinaryFile(String F_NAME);

	int fireremoveLastProduct();

	int fireSendUpdateMassages(String massage);

	void fireShowAllCustomersWhoRecievedMSG();

	void fireAddProductToFile();

	void fireAddProductToMap(String clientName, String phoneNum, boolean clientInterested, String productNameString,
			double costPrice, double sellingPrice,long epochTime,String catalogNum);
}