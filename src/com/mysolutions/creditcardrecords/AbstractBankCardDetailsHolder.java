package com.mysolutions.creditcardrecords;

import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractBankCardDetailsHolder<K, V> {

	private Map<K, V> cardData;

	public AbstractBankCardDetailsHolder() {
		cardData = new TreeMap<K, V>();
	}
	
	public AbstractBankCardDetailsHolder(K key, V value){
		cardData.put(key, value);
	}
	
	public abstract Map<K, V> getAllCardDetails();
	
	public abstract void setAllCardDetails(Map<K,V> cardDetails);	
	
}
