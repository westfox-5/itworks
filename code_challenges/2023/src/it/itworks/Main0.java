package it.itworks;

import it.itworks.models.Matrix;
import it.itworks.reader.MatrixReader;

public class Main0 {
    public static void main(String[] args) throws Exception {
    	
    	Matrix matrix = new MatrixReader().parseFile("code_challenges/2023/input/00-example.txt");
    	System.out.println();
    }

}
