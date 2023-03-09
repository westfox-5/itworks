package it.itworks.solution;

import it.itworks.models.Cell;
import it.itworks.models.Matrix;
import it.itworks.models.Snake;
import it.itworks.models.StrengthMatrix;

import java.util.*;

public class Solution0 extends Solution {

	protected Solution0() {
		super("00-example.txt");
	}

    @Override
	protected void execute(Matrix matrix) {
		StrengthMatrix strengthMatrix = calculateStrengthMatrix(matrix);

		List<Snake> snakesToPlace = new ArrayList<>(matrix.getSnakes());
		// Sort for descending order
		snakesToPlace.sort(Comparator.comparingInt(Snake::getLength).reversed());

		int numSnakes = matrix.getNumberSnakes();

		for(int snakesPlaced = 0; snakesPlaced < numSnakes; snakesPlaced++) {
			// Get Current Max Position
			int[] currentMaxPosition = strengthMatrix.getMaxCell();

			Snake currentSnake = snakesToPlace.remove(0);
			Cell cella = matrix.getCella(currentMaxPosition[0], currentMaxPosition[1]);
			List<Cell> path = matrix.placeSnake(currentSnake, cella);
			
			

			// Update strength matrix
			strengthMatrix.resetCells(path);
			
			
			
		}

		System.out.println("A");
	}

	@Override
	protected StrengthMatrix calculateStrengthMatrix(Matrix matrix) {
		// calculate medium length of snakes
		int snakeLength = matrix.getSnakes().stream().map(Snake::getLength).reduce(0, Integer::sum) / matrix.getNumberSnakes();

		snakeLength = Math.min(Math.min(matrix.getR(), matrix.getC()) / 2, snakeLength);

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

		int[][] rombo = getRombo(snakeLength * 2 + 1);

		List<int[]> alreadyEvaluated = new ArrayList<>();
		int currentRow = 0;
		for(int i = x - snakeLength; i <= x + snakeLength; i++) {
			int currentCol = 0;
			for(int q = y - snakeLength; q <= y + snakeLength; q++) {
				int safeI = matrix.getSafeX(i);
				int safeQ = matrix.getSafeY(q);

				// Check if already used this coordinates
				boolean found = false;
				for(int[] pair : alreadyEvaluated) {

					if (pair[0] == safeI && pair[1] == safeQ) {
						found = true;
						break;
					}
				}

				if(!found) {
					Integer cellValue = matrix.getCella(i, q).getValue();
					if(cellValue != null) {
						sum += cellValue * rombo[currentRow][currentCol];
					}
					// keep tracking
					alreadyEvaluated.add(new int[]{safeI, safeQ});
				}

				currentCol++;
			}
			currentRow++;
		}
		return sum;
	}

	public int[][] getRombo(int n) {
		int[][] matrix = new int[n][n]; // crea la matrice quadrata
		int mid = n / 2; // calcola l'indice centrale
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i <= mid && j >= mid - i && j <= mid + i) { // triangolo superiore del rombo
					matrix[i][j] = 1;
				} else if (i > mid && j >= mid - (n - i - 1) && j <= mid + (n - i - 1)) { // triangolo inferiore del rombo
					matrix[i][j] = 1;
				}
			}
		}

		return matrix;
	}
}
