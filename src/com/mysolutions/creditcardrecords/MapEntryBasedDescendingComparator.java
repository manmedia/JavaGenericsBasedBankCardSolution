package com.mysolutions.creditcardrecords;

import java.util.Comparator;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class MapEntryBasedDescendingComparator implements Comparator {
	

	@Override
	@SuppressWarnings({"unchecked"})
	public int compare(Object o1, Object o2) {
		return ((Comparable) ((Map.Entry) (o2)).getValue())
				.compareTo((Comparable) ((Map.Entry) (o1)).getValue());
	}

}
