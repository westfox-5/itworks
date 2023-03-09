package it.itworks.models;

import java.util.ArrayList;
import java.util.List;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;
import it.itworks.annotations.InputCollection;
import it.itworks.annotations.Output;
import it.itworks.annotations.OutputCollection;

@InputClass
public class Matrix {

	@Input(position = 1, max = 5_001)
	Integer C;

	@Input(position = 2, max = 5_001)
	Integer R;

	@Input(position = 3, max = 5_001)
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

	public List<Cell> placeSnake(Snake snake, Cell cell) {
		snake.setCurrentCell(cell);

		ArrayList<Cell> path = new ArrayList<Cell>();
		calcolaBestPath2(snake, cell, false, path);

		return path;
	}

	public Cell getCella(int x, int y) {
		int safeX = getSafeX(x);
		int safeY = getSafeY(y);
		return this.celle[safeX][safeY];
	}

	private Integer calcolaBestPath2(Snake snake, Cell cell, boolean noWh, List<Cell> path) {
		if (noWh && cell.isWormhole()) {
			return null;
		} else {
			// todo
			cell = followWh(cell);
		}
		if (snake.currentLength == 1) {
			path.add(cell.clone());
			return cell.getValue();
		} else {
			path.add(cell.clone());

			return cell.getValue() + max(calcolaBestPath2(snake, snake.goDown(noWh), noWh, path),
					calcolaBestPath2(snake, snake.goUp(noWh), noWh, path),
					calcolaBestPath2(snake, snake.goRight(noWh), noWh, path),
					calcolaBestPath2(snake, snake.goLeft(noWh), noWh, path));
		}
	}

	private Cell followWh(Cell cell) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer max(Integer a, Integer b, Integer c, Integer d) {
		return a + b + c + d;
	}

//	private void calcolaBestPath(Integer length, Cell cell, Integer value) {
//	        int maxSum = Integer.MIN_VALUE;
//            ArrayList<Cell> maxPath = new ArrayList<Cell>();
//
//	        for (int i = 0; i < R - length + 1; i++) {
//	            for (int j = 0; j < C - length + 1; j++) {
//	                int currentSum = 0;
//	                ArrayList<Cell> currentPath = new ArrayList<Cell>();
//	                
//	                for (int k = 0; k < length; k++) {
//	                    Cell cella = getCella(i+k,j+k);
//	                    if (!cella.isWormhole())
//	                    	currentSum += value; 
//	                    currentPath.add(index, cella) (i+k) * matrix[0].length + (j+k);
//	                }
//	                
//	                if (currentSum > maxSum) {
//	                    maxSum = currentSum;
//	                    maxPath = currentPath;
//	                }
//	            }
//	        }
//	        
//	        return maxPath;
//		}

	public int getSafeX(int x) {
		int safeX = (x + getR()) % getR();
		return safeX;
	}

	public int getSafeY(int y) {
		int safeY = (y + getC()) % getC();
		return safeY;
	}
}
