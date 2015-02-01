package com.mysolutions.creditcardrecords;

public class CompositeKeyImplementer<K1, K2> implements
		CompositeKeyType<K1, K2> {

	private K1 key1;
	private K2 key2;

	public CompositeKeyImplementer() {
		this.key1 = null;
		this.key2 = null;
	}

	public CompositeKeyImplementer(K1 key1, K2 key2) throws IllegalArgumentException {
		
		if (key1.equals(key2)){
			throw new IllegalArgumentException("both keys cannot be equal");
		}
		this.key1 = key1;
		this.key2 = key2;
	}
	
	public CompositeKeyImplementer(CompositeKeyImplementer<K1,K2> key) throws IllegalArgumentException {
		
		this(key.getKey1(), key.getKey2());
	}

	@Override
	public K1 getKey1() {

		return this.key1;
	}

	@Override
	public K2 getKey2() {
		return this.key2;
	}
	
	/**
	 * IMPORTANT - NEEDS TO OVERRIDE THESE TWO FOR MAINTAINING FUNDAMETNAL CRUD OPERATION
	 * 
	 * 
	 */	

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CompositeKeyImplementer<?, ?>)) {
			return false;
		}

		if (!(((CompositeKeyImplementer<?, ?>) obj).getKey1().equals(this.key1))
				|| !(((CompositeKeyImplementer<?, ?>) obj).getKey2()
						.equals(this.key2))) {
			return false;
		}

		return true;

	}
	
    @Override
    public int hashCode() {
        return (this.key1.hashCode() + this.key2.hashCode());
    }

}
