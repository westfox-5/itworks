package it.itworks.models;

import it.itworks.annotations.Input;

public class Cell {
	
	private final Matrix matrix;
	private final int r, c;
	private boolean empty;
	
	public Cell(Matrix matrix, int r, int c) {
		this.matrix = matrix;
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

	public Cell goLeft(){
		if(c==0)
			return matrix.getCelle()[r][matrix.C-1];
		else	
			return matrix.getCelle()[r][c-1];
	}

	public Cell goRight(){
		if(c==matrix.C-1)
			return matrix.getCelle()[r][0];
		else	
			return matrix.getCelle()[r][c+1];
	}

	public Cell goUp(){
		if(r==0)
			return matrix.getCelle()[matrix.R-1][c];
		else	
			return matrix.getCelle()[r-1][c];
	}

	public Cell goDown(){
		if(r==matrix.R-1)
			return matrix.getCelle()[0][c];
		else	
			return matrix.getCelle()[r+1][c];
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
		Cell cell = new Cell(matrix, r, c);
		cell.wh = wh;
		cell.empty = empty;
		cell.value = value;
		
		return cell;
	}
}
