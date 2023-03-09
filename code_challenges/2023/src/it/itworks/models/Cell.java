package it.itworks.models;

import it.itworks.annotations.Input;

public class Cell {
	
	private final int r, c;
	private boolean empty;
	
	public Cell(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Input(position =  1)
	private String value;
	
	
	public Integer getValue() {
		if (value.equals("*")) return null;
		return Integer.valueOf(value);
	}


	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public void setValue(String value) {
		this.value = value;
	}	
}
