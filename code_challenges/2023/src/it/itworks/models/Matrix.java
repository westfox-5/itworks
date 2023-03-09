package it.itworks.models;

import java.util.List;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;
import it.itworks.annotations.InputCollection;
import it.itworks.annotations.Output;
import it.itworks.annotations.OutputCollection;

@InputClass
public class Matrix {
	
	@Input(position = 1)
	Integer C;
	
	@Input(position = 2)
	Integer R;
	
	@Input(position =  3)
	Integer S;
	
	@Input(position = 4)
	@InputCollection(Snake.class)
	private List<Snake> snakes;
	
	
	@Input(position = 4)
	@InputCollection(Cell.class)
	private Cell[][] celle;

	@Output(position = 1)
	@OutputCollection(Snake.class)
	private List<Snake> outputSnakes;
	
	
	public Integer getC() {
		return C;
	}

	public void setC(Integer c) {
		C = c;
	}

	public Integer getR() {
		return R;
	}

	public void setR(Integer r) {
		R = r;
	}

	public Integer getS() {
		return S;
	}

	public void setS(Integer s) {
		S = s;
	}

	public List<Snake> getSnakes() {
		return snakes;
	}

	public void setSnakes(List<Snake> snakes) {
		this.snakes = snakes;
	}

	public Cell[][] getCelle() {
		return celle;
	}

	public void setCelle(Cell[][] celle) {
		this.celle = celle;
	}

	public List<Snake> getOutputSnakes() {
		return outputSnakes;
	}

	public void setOutputSnakes(List<Snake> outputSnakes) {
		this.outputSnakes = outputSnakes;
	}



	public Integer getNumberSnakes() {
		return snakes.size();
	}

	public void placeSnake(Snake snake, Cell cell) {
		
	}

}
