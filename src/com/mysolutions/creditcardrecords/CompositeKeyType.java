package com.mysolutions.creditcardrecords;

public interface CompositeKeyType<K1, K2> {

	public K1 getKey1();
	public K2 getKey2();
}
