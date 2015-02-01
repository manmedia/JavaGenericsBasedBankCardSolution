package com.mysolutions.creditcardrecords;

import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractBankCardDetailsHolder<K, V> {

	protected Map<K, V> cardDetails;

	public AbstractBankCardDetailsHolder() {
		cardDetails = new TreeMap<K, V>();
	}
	
	public AbstractBankCardDetailsHolder(K key, V value){
		cardDetails.put(key, value);
	}
	
	public abstract Map<K, V> getAllCardDetails();
	
	public abstract void setAllCardDetails(Map<K,V> cardDetails);	
	
}

