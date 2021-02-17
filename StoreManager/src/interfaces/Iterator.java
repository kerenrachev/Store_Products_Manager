package interfaces;

import java.util.Map.Entry;

import Model.Product;

public interface Iterator {
	public boolean hasNext();
	public Entry<String, Product> next();
	public void remove();
}

