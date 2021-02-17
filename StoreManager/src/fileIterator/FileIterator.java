package fileIterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import Model.Product;
import Model.Store;
import file_iO.File_IO;
import interfaces.Iterator;

public class FileIterator {
	
	public Iterator getIterator(String FileName) {
		return new ConcreteFileIterator(FileName);	
	}


	private class ConcreteFileIterator implements Iterator{
		
		private File f;
		private int size;
		private RandomAccessFile raf;
		private byte[] binKey;
		private byte[] binProduct;
		
		private int current =0;
		private int last =-1;
		
		public ConcreteFileIterator(String fileName) {
			f = new File(fileName);
			try {
				raf = new RandomAccessFile(fileName, "rw");
				int size = (int)(raf.length());// / (Store.PRODUCT_KEY_SIZE + Product.PRODUCT_SIZE));				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public boolean hasNext() {
			return current<size;
		}

		@Override
		public Map.Entry<String, Product> next() {
			if (!hasNext())
				throw new NoSuchElementException();

			try {
				String k = File_IO.readFixedString(Store.PRODUCT_KEY_SIZE, raf);
				Product p = Product.readProductToFile(raf);
				MyEntry entry = new MyEntry(k, p);
				
				last = current;
				current += (Store.PRODUCT_KEY_SIZE + Product.PRODUCT_SIZE);	
				return entry;
 
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public void remove() {
			if (last == -1)
				throw new IllegalStateException();
			try {
				while(hasNext()) {
					int pos = last;
					raf.seek(current);
					String k = File_IO.readFixedString(Store.PRODUCT_KEY_SIZE, raf);
					Product p = Product.readProductToFile(raf);
					raf.seek(pos);
					File_IO.readFixedString(Store.PRODUCT_KEY_SIZE, raf);
					p.writeProductToFile(raf);
					
				}
				raf.setLength(size - Store.PRODUCT_KEY_SIZE - Product.PRODUCT_SIZE);
				last =-1;
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		private class MyEntry implements Map.Entry<String, Product>{
			
			private String key;
			private Product value;
			
			public MyEntry(final String key ,  Product value) {
				this.key = key;
				this.value = value;
			
			}

			@Override
			public String getKey() {
				return key;
			}

			@Override
			public Product getValue() {
				return value;
			}

			@Override
			public Product setValue(Product value) {
				Product old = this.value;
				this.value = value;
				return old;
			}

		}
	}



}


