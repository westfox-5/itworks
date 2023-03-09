package it.itworks.models;

import it.itworks.annotations.Input;

public class Cell {

	@Input(position =  1)
	private String value;
	
	
	public Integer getValue() {
		if (value.equals("*")) return null;
		return Integer.valueOf(value);
	}
}
