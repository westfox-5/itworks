package it.itworks.solution;

import it.itworks.models.Cell;
import it.itworks.models.Matrix;
import it.itworks.models.Snake;
import it.itworks.models.StrengthMatrix;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution0 extends Solution {

	protected Solution0() {
		super("00-example.txt");
	}

    @Override
	protected void execute(Matrix matrix) {
		StrengthMatrix strengthMatrix = calculateStrengthMatrix();

		int[] currentMaxPosition = new int[]{ 0, 0};

		List<Snake> snakesToPlace = matrix.getSnakes().stream().map(snake -> snake.clone()).toList();
		// Sort for descending order
		snakesToPlace.sort(Comparator.comparingInt(Snake::getLength).reversed());

		for(int snakesPlaced = 0; snakesPlaced <= matrix.getNumberSnakes(); snakesPlaced++) {
			// Get Current Max Position
			currentMaxPosition = strengthMatrix.getMaxCell();

			Snake currentSnake = snakesToPlace.remove(0);
			Cell cella = matrix.getCella(currentMaxPosition[0], currentMaxPosition[1]);
			matrix.placeSnake(currentSnake, cella);

			
		}


	}

	@Override
	protected StrengthMatrix calculateStrengthMatrix() {
		return null;
	}

}
