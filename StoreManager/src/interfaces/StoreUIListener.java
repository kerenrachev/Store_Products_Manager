package interfaces;

import Model.Product;
import javafx.scene.control.TextField;

public interface StoreUIListener {

	void executeCommand(Command c);

	void fireSearchForCatalogeNumber(String strCatalogNumber);

	void fireRemoveProductByCatalogeNumber(String catalogNumber);

	void fireUpdateMapType(int mapType);

	int firereadProductsFromBinaryFile(String F_NAME);

	void fireAddProduct(String catalogNumber, Product p);

	int fireremoveLastProduct();

	int fireSendUpdateMassages();
}
