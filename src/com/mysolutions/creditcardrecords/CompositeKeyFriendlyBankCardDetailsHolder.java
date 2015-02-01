package com.mysolutions.creditcardrecords;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that generalises Key-Value based card details holder and compatible
 * with Composite keys
 * 
 * @author manna
 *
 * @param <K>
 * @param <V>
 */

public class CompositeKeyFriendlyBankCardDetailsHolder<K, V> extends
		AbstractBankCardDetailsHolder<K, V> {

	/* Data holder */
	private Map<K, V> cardDetails;

	/* Constructors */
	public CompositeKeyFriendlyBankCardDetailsHolder() {
		cardDetails = new HashMap<K, V>();
	}

	public CompositeKeyFriendlyBankCardDetailsHolder(K key, V value) {
		this();
		this.cardDetails.put(key, value);
	}

	public CompositeKeyFriendlyBankCardDetailsHolder(Map<K, V> cardDetails) {
		this();
		this.cardDetails = cardDetails;
	}

	/* Methods */
	@Override
	public Map<K, V> getAllCardDetails() {
		return (HashMap<K, V>) (this.cardDetails);
	}
	
	@Override
	public void setAllCardDetails(Map<K, V> cardDetails) {
		this.cardDetails = new HashMap<K, V>(cardDetails);
	}

	public Map<K, V> getIndividualCardDetails(K key) {
		Map<K, V> map = new HashMap<K, V>();
		map.put(key, this.cardDetails.get(key));
		return map;
	}

	public void putEntry(K key, V value) {
		this.cardDetails.put(key, value);
	}

	public V removeEntry(K key) {
		return ((this.cardDetails.containsKey(key)) ? (this.cardDetails
				.remove(key)) : (null));

	}

	/**
	 * IMPORTANT - NEEDS TO OVERRIDE THESE TWO FOR MAINTAINING FUNDAMETNAL CRUD OPERATION
	 * 
	 * 
	 */
	@Override
	public boolean equals(Object o) {

		if (!(o instanceof CompositeKeyFriendlyBankCardDetailsHolder<?, ?>)) {
			return false;
		}

		if (!(((CompositeKeyFriendlyBankCardDetailsHolder<?, ?>) o)
				.getAllCardDetails().equals(this.cardDetails))) {
			return false;
		}

		return true;

	}
	
	@Override
	public int hashCode(){
		return this.cardDetails.hashCode()*31;
	}

}
