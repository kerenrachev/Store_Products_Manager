package Model;

import java.util.Comparator;

public class CompairProductByDescendingID implements Comparator<String>{

	@Override
	public int compare(String first, String second) {
		return (first.compareTo(second) *-1);
	}

}
