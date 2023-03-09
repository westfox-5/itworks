package it.itworks.models;

import java.util.ArrayList;
import java.util.List;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;
import it.itworks.annotations.Output;

@InputClass
public class Snake {

	@Input(position = 1, min = 0, max = 1_000)
	Integer length;

	@Output(position = 1)
	Integer ci; // col iniziale inserimento snake

	@Output(position = 2)
	Integer ri; // row iniziale inserimento snake

	@Output(position = 3)
	List<Cell> path; // lista di celle che occupa lo snake

	Cell currentCell;

	Integer currentLength;
	
	Matrix matrix;
	
	public void init() {
		currentCell=new Cell(matrix, ri, ci);
	}
	
	public Cell goRight() {
		return moveTo("R");
	}

	public Cell goLeft() {
		return moveTo("L");
	}

	public Cell goUp() {
		return moveTo("U");
	}

	public Cell goDown() {
		return moveTo("D");
	}

	private Cell moveTo(String direction) {
		
		currentCell.setEmpty(false);
		switch (direction) {
		case "R":
			return currentCell.goRight();
		case "L":
			return currentCell.goLeft();
		case "U":
			return currentCell.goUpper();
		case "D":
			return currentCell.goDown();
			break;

		default:
			break;
		}
		return null;
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
		s.currentCell = currentCell.clone();
		
		s.path = new ArrayList<Cell>();
		for (Cell c: path) {
			s.path.add(c.clone());
		}
		return s;
	
	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

}
