package it.itworks.solution;

import it.itworks.models.Cell;
import it.itworks.models.Matrix;
import it.itworks.models.Snake;
import it.itworks.models.StrengthMatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution0 extends Solution {

	protected Solution0() {
		super("00-example.txt");
	}

    @Override
	protected void execute(Matrix matrix) {
		StrengthMatrix strengthMatrix = calculateStrengthMatrix(matrix);

		List<Snake> snakesToPlace = new ArrayList<>(matrix.getSnakes().stream().map(Snake::clone).toList());
		// Sort for descending order
		snakesToPlace.sort(Comparator.comparingInt(Snake::getLength).reversed());

		int numSnakes = matrix.getNumberSnakes();

		for(int snakesPlaced = 0; snakesPlaced <= numSnakes; snakesPlaced++) {
			// Get Current Max Position
			int[] currentMaxPosition = strengthMatrix.getMaxCell();

			Snake currentSnake = snakesToPlace.remove(0);
			Cell cella = matrix.getCella(currentMaxPosition[0], currentMaxPosition[1]);
			List<Cell> occupate = matrix.placeSnake(currentSnake, cella);

			// Update strength matrix
			strengthMatrix.resetCells(occupate);
		}


	}

	@Override
	protected StrengthMatrix calculateStrengthMatrix(Matrix matrix) {
		// calculate medium length of snakes
		int snakeLength = matrix.getSnakes().stream().map(Snake::getLength).reduce(0, Integer::sum) / matrix.getNumberSnakes();

		StrengthMatrix strengthMatrix = new StrengthMatrix(matrix.getR(), matrix.getC());

		for(int x = 0; x < matrix.getR(); x++) {
			for(int y = 0; y < matrix.getC(); y++) {
				int strength = calculateValue(matrix, x, y, snakeLength);
				strengthMatrix.set(x, y, strength);
			}
		}

		return strengthMatrix;
	}


	private int calculateValue(Matrix matrix, int x, int y, int snakeLength) {
		int sum = 0;
		for(int i = x - snakeLength; i <= x + snakeLength; i++) {
			for(int q = x - snakeLength; q <= x + snakeLength; q++) {
				Integer cellValue = matrix.getCella(i, q).getValue();
				if(cellValue != null) {
					sum += cellValue;
				}
			}
		}
		return sum;
	}

}
