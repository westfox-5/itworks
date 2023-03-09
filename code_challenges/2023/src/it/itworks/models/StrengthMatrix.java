package it.itworks.models;

import java.util.List;

public class StrengthMatrix {
    Integer[][] strengthMatrix;

    public StrengthMatrix(int r, int c) {
        strengthMatrix = new Integer[r][c];
    }

    public void set(int r, int c, int val) {
        this.strengthMatrix[r][c] = val;
    }

    public int[] getMaxCell() {
        int[] currentMaxPosition = new int[]{0, 0};
        int max = Integer.MIN_VALUE;

        for(int x = 0; x < strengthMatrix.length; x++) {
            for(int y = 0; y < strengthMatrix[0].length; y++) {
                if(strengthMatrix[x][y] != null && strengthMatrix[x][y] >= max) {
                    currentMaxPosition[0] = x;
                    currentMaxPosition[1] = y;
                    max = strengthMatrix[x][y];
                }
            }
        }

        return currentMaxPosition;
    }

    public int getValue(int x, int y) {
        return this.strengthMatrix[x][y];
    }

    public void resetCells(List<Cell> toReset) {
        for(Cell c : toReset) {
            strengthMatrix[c.getR()][c.getC()] = null;
        }
    }
}
