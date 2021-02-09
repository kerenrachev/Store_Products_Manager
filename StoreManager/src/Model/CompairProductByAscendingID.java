package Model;

import java.util.Comparator;

public class CompairProductByAscendingID implements Comparator<String> {

	@Override
	public int compare(String first, String second) {
		return first.compareTo(second);
	}

}
