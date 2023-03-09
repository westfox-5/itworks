package it.itworks.solution;

import it.itworks.models.Matrix;
import it.itworks.models.StrengthMatrix;
import it.itworks.reader.MatrixReader;
import it.itworks.writer.MatrixWriter;

public abstract class Solution {
	
	public static void main(String[] args) throws Exception {
    	Solution solution = new Solution0();
    	
    	Matrix matrix = solution.read();
    	
    	solution.execute(matrix);
    
    	solution.write(matrix);
    }

	
	protected String file;
	
	protected Solution(String file) {
		this.file = file;
	}	
	
    protected abstract void execute(Matrix matrix);
    
    protected Matrix read() throws Exception {
    	return new MatrixReader().parseFile("input/"+file);
    }

    protected void write(Matrix matrix) throws Exception {
    	new MatrixWriter().writeToFile(matrix, "output/"+file);
    }

	protected abstract StrengthMatrix calculateStrengthMatrix(Matrix matrix);
}
