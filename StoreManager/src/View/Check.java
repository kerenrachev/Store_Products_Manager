package View;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.Map.Entry;

import Model.Customer;
import Model.Product;
import Model.Store;
import store_Commands.AddProductCommand;

public class Check {
	
	public Check(Store store)
	{
		final String F_NAME = "products txt";
		//command list
		AddProductCommand addProductCommand;

		File f = new File(F_NAME);
		
		if(!f.exists())
		{
			Customer c1 = new Customer("mosha","050001",true);
			Product  p1 = new Product("Bamba",10,15,c1);
			String key1 = "2001";
			
			Customer c2 = new Customer("yossi","050002",true);
			Product  p2 = new Product("bisli",4,9,c2);
			String key2 = "2002";

			Customer c3 = new Customer("niv","050003",true);
			Product  p3 = new Product("kinder",8,16,c3);
			String key3 = "2003";

			Customer c4 = new Customer("yoval","050004",true);
			Product  p4 = new Product("chips",5,10,c4);
			String key4 = "2004";

			Customer c5 = new Customer("amit","050005",true);
			Product  p5 = new Product("kitkat",13,15,c5);
			String key5 = "2005";

			Customer c6 = new Customer("or","050006",true);
			Product  p6 = new Product("cake",22,25,c6);
			String key6 = "2006";
			
			addProductCommand = new AddProductCommand(store, key1 , p1);
			addProductCommand.execute();
			addProductCommand = new AddProductCommand(store, key2 , p2);
			addProductCommand.execute();
			addProductCommand = new AddProductCommand(store, key3 , p3);
			addProductCommand.execute();
			addProductCommand = new AddProductCommand(store, key4 , p4);
			addProductCommand.execute();
			addProductCommand = new AddProductCommand(store, key5 , p5);
			addProductCommand.execute();
			addProductCommand = new AddProductCommand(store, key6 , p6);
			addProductCommand.execute();

		}
		else {
			try {
				store.readProductsFromBinaryFile(F_NAME);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		Map<String, Product> map = store.getMap();
		
		for(Entry<String, Product> e : map.entrySet())
		{
			System.out.println(e.getKey() + e.getValue());
		}

	}

}
