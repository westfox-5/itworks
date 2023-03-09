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
		Cell cell = new Cell(r, c);
		cell.wh = wh;
		cell.empty = empty;
		cell.value = value;
		
		return cell;
	}
}
