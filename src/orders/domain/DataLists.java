package orders.domain;

import java.util.ArrayList;
import java.util.List;

public class DataLists implements IDataStore{

	private List<Item> items;
	
	public DataLists(){
		items = new ArrayList<Item>();
	}
}
