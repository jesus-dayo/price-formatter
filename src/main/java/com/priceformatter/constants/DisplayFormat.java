package com.priceformatter.constants;

public enum DisplayFormat {

	DECIMAL(0, 1), PERCENTAGE(1, 100), PERMILE(2, 1000), BASISPOINT(3, 10000);

	DisplayFormat(int key, int factor) {
		this.key = key;
		this.factor = factor;
	}

	private int key;
	private int factor;

	public int getKey() {
		return key;
	}

	public int getFactor() {
		return factor;
	}
	
	public static int getFactorByKey(int key){
		for(DisplayFormat format:DisplayFormat.values()) {
			if(format.getKey() == key) {
				return format.getFactor(); 
			}
		}
		throw new IllegalArgumentException("Invalid format provided. Valid values are 0=decimal,1=percentage,2=permile,3=basispoint.");
	}
	
}
