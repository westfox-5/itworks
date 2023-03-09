package it.itworks.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;
import it.itworks.annotations.InputCollection;
import it.itworks.annotations.Output;
import it.itworks.annotations.OutputClass;
import it.itworks.annotations.OutputCollection;

@InputClass
@OutputClass
public class Matrix {

	@Input(position = 1, max = 5_001)
	Integer C;

	@Input(position = 2, max = 5_001)
	Integer R;

	@Input(position = 3, max = 5_001)
	Integer S;

	@Input(position = 4)
	@InputCollection(Snake.class)
	@Output(position = 1)
	@OutputCollection(Snake.class)
	private List<Snake> snakes;

	@Input(position = 4)
	@InputCollection(Cell.class)
	private Cell[][] celle;

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


	public Integer getNumberSnakes() {
		return snakes.size();
	}

	public List<Cell> placeSnake(Snake snake, Cell cell) {
		snake.setCurrentCell(cell);
		snake.ci = cell.getC();
		snake.ri = cell.getR();
		
		ArrayList<Cell> path = new ArrayList<Cell>();
		calcolaBestPath2(snake, cell, true, path);
		
		snake.setPath(path);
		return path;
	}

	public Cell getCella(int x, int y) {
		int safeX = getSafeX(x);
		int safeY = getSafeY(y);
		return this.celle[safeX][safeY];
	}

	private Integer calcolaBestPath2(Snake snake, Cell cell, boolean noWh, List<Cell> path) {
		if (cell==null) {
			return null;
		}
		if (noWh && cell.isWormhole()) {
			return null;
		} else if (!noWh && cell.isWormhole()){
			// todo
			cell = followWh(cell);
		}
		
		
		if (snake.currentLength == 1) {
			path.add(cell.clone());
			return cell.getValue();
		} else {
			path.add(cell.clone());
			
			List<Cell> pathD = new ArrayList<>(path);
			Snake snakeD = snake.clone();
			Integer scoreD = calcolaBestPath2(snakeD, snakeD.goDown(noWh), noWh, pathD);
			
			List<Cell> pathU = new ArrayList<>(path);
			Snake snakeU = snake.clone();
			Integer scoreU = calcolaBestPath2(snakeU, snakeU.goUp(noWh), noWh, pathU);
			
			
			List<Cell> pathL = new ArrayList<>(path);
			Snake snakeL = snake.clone();
			Integer scoreL = calcolaBestPath2(snakeL, snakeL.goLeft(noWh), noWh, pathL);
			
			
			List<Cell> pathR = new ArrayList<>(path);
			Snake snakeR = snake.clone();
			Integer scoreR = calcolaBestPath2(snakeR, snakeR.goRight(noWh), noWh, pathR);
			
			
			
			
			Integer maxScore = scoreD != null ? scoreD: Integer.MIN_VALUE;
			List<Cell> maxPath = pathD;
			Snake maxSnake = snakeD;
			
			if (scoreU != null && maxScore.compareTo(scoreU) <= 0) {
				maxScore = scoreU;
				maxPath = pathU;
				maxSnake = snakeU;
			}
			
			if (scoreL != null && maxScore.compareTo(scoreL) <= 0) {
				maxScore = scoreL;
				maxPath = pathL;
				maxSnake = snakeL;
			}
			
			if (scoreR != null && maxScore.compareTo(scoreR) <= 0) {
				maxScore = scoreR;
				maxPath = pathR;
				maxSnake = snakeR;
			}
			
			path.clear();
			path.addAll(maxPath);
			return cell.getValue() + maxScore;
		}
	}
	
	private Cell followWh(Cell cell) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer max(Integer a, Integer b, Integer c, Integer d) {
		return Arrays.asList(a,b,c,d).stream().mapToInt(p->p==null?0:p).max().getAsInt();
	}

	public int getSafeX(int x) {
		int safeX = (x + getR()) % getR();
		return safeX;
	}

	public int getSafeY(int y) {
		int safeY = (y + getC()) % getC();
		return safeY;
	}
}
