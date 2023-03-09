package it.itworks.models;

import it.itworks.annotations.Input;

public class Cell {
	
	private final int r, c;
	private boolean empty;
	
	public Cell(int r, int c) {
		this.r = r;
		this.c = c;
	}

	private boolean wh;

	@Input(position =  1, min = -10_001, max = 10_000)
	private Integer value;

	
	public Integer getValue() {
		if (wh) return null;
		return value;
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

	public boolean isWormhole() {
		return wh;
	}

	public void setWh(boolean wh) {
		this.wh = wh;
	}


	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public void setValue(Integer value) {
		this.value = value;
	}	
	
	
	public Cell clone() {
		Cell c = new Cell(r, c);
		c.wh = wh;
		c.empty = empty;
		c.value = value;
		
		return c;
	}
}
