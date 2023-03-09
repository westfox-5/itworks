package it.itworks.models;

import java.util.ArrayList;
import java.util.List;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;
import it.itworks.annotations.Output;
import it.itworks.annotations.OutputClass;
import it.itworks.annotations.OutputCollection;

@InputClass
@OutputClass
public class Snake {

	@Input(position = 1, min = 0, max = 1_000)
	Integer length;

	@Output(position = 1)
	Integer ci; // col iniziale inserimento snake

	@Output(position = 2)
	Integer ri; // row iniziale inserimento snake
	
	@Output(position = 3)
	@OutputCollection(Cell.class)
	List<Cell> path; // lista di celle che occupa lo snake

	Cell currentCell;

	public Integer currentLength;
	
	Matrix matrix;
	
	public void init() {
		currentCell=new Cell(matrix, ri, ci);
	}
	
	public Cell goRight(boolean noWh) {
		return moveTo("R", noWh);
	}

	public Cell goLeft(boolean noWh) {
		return moveTo("L", noWh);
	}

	public Cell goUp(boolean noWh) {
		return moveTo("U", noWh);
	}

	public Cell goDown(boolean noWh) {
		return moveTo("D", noWh);
	}

	private Cell moveTo(String direction,boolean noWh) {
		
		
		currentLength=currentLength-1;
		switch (direction) {
		case "R":
			Cell c = checkWh(noWh,currentCell.goRight());
			if (c != null)c.setDir("R");
			return c;
		case "L":
			c = checkWh(noWh,currentCell.goLeft());
			if (c != null)c.setDir("L");
			return c;
		case "U":
			c = checkWh(noWh,currentCell.goUp());
			if (c != null)c.setDir("U");
			return c;
		case "D":
			c = checkWh(noWh,currentCell.goDown());
			if (c != null) c.setDir("D");
			return c;

		default:
			break;
		}
		return null;
	}

	private Cell checkWh(boolean noWh, Cell cell) {
		if (!cell.isEmpty())
			 return null;
		cell.setEmpty(false);
		 return cell;
	}

	public List<Cell> getPath() {
		return path;
	}

	public void setPath(List<Cell> path) {
		this.path = path;
	}

	public Cell getCurrentCell() {
		return currentCell;
	}

	public void setCurrentCell(Cell currentCell) {
		this.currentCell = currentCell;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getCi() {
		return ci;
	}

	public void setCi(Integer ci) {
		this.ci = ci;
	}

	public Integer getRi() {
		return ri;
	}

	public void setRi(Integer ri) {
		this.ri = ri;
	}

	public Snake clone() {
		Snake s = new Snake();
		s.length = length;
		s.ci = ci;
		s.ri = ri;
		s.currentCell = currentCell != null ? currentCell.clone() : null;
		s.currentLength = currentLength;
		
		s.path = new ArrayList<Cell>();
		if(path != null) {
			for (Cell c: path) {
				s.path.add(c.clone());
			}
		}
		return s;	
	}
	
	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

}
