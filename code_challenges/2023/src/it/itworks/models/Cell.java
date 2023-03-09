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

	public Cell goLeft(Matrix matrix){
		if(c<0)
			return matrix.getCelle()[matrix.C-1][r];
		else	
			return matrix.getCelle()[c-1][r];
	}

	public Cell goRight(Matrix matrix){
		if(c>matrix.C)
			return matrix.getCelle()[0][r];
		else	
			return matrix.getCelle()[c+1][r];
	}

	public Cell goUp(Matrix matrix){
		if(r<0)
			return matrix.getCelle()[c][matrix.R-1];
		else	
			return matrix.getCelle()[c][r-1];
	}

	public Cell goDown(Matrix matrix){
		if(r>matrix.R)
			return matrix.getCelle()[c][0];
		else	
			return matrix.getCelle()[c][r+1];
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
