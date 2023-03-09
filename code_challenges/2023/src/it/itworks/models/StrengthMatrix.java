package it.itworks.models;

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
        int max = -1;

        for(int x = 0; x < strengthMatrix.length; x++) {
            for(int y = 0; y < strengthMatrix[0].length; y++) {
                if(strengthMatrix[x][y] >= max) {
                    currentMaxPosition[0] = x;
                    currentMaxPosition[1] = y;
                    max = strengthMatrix[x][y];
                }
            }
        }

        return currentMaxPosition;
    }
}
