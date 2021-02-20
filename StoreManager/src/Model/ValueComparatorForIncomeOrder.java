package Model;

import java.util.Comparator;
import java.util.Map;

public class ValueComparatorForIncomeOrder implements Comparator<String> {

	Map<String, Product> base;

    public ValueComparatorForIncomeOrder(Map<String, Product> base) {
        this.base = base;
    }

	@Override
	public int compare(String o1, String o2) {
		if (((Product)base.get(o1)).getTimeStamp() >= ((Product)base.get(o2)).getTimeStamp())
				 return 1;
		else return -1;
	}


}
