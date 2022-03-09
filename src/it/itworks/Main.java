package it.itworks;

import it.itworks.example.*;
import it.itworks.reader.InputReader;
import it.itworks.writer.OutputWriter;

public class Main {
    public static void main(String[] args) {
        String inFilepath = "tests/example1.in";
        String outFilepath = "tests/example1.out";

        System.out.println("Reading input file '"+inFilepath+"'...");
        Matrix matrix = read(inFilepath);
        if (matrix == null) {
            throw new RuntimeException("Failed to parse input file.");
        }
        System.out.println("Success!");

        System.out.println("-------------------------");
        System.out.println("Rows: " + matrix.getRows());
        System.out.println("Cols: " + matrix.getCols());

        System.out.println("Antennas: ");
        for (Antenna e: matrix.getAntennas()) {
            System.out.println("[x: "+ e.getX() +"; y: " + e.getY() + "; velocity: "+ e.getVelocity() + "]");
        }

        System.out.println("Buildings: ");
        for (Building e: matrix.getBuildings()) {
            System.out.println("[x: "+ e.getX() +"; y: " + e.getX() + "; dimension: "+ e.getDimension() + "]");
        }


         write(matrix, outFilepath);
    }

    private static Matrix read(String filepath) {
        try {
            InputReader<Matrix> reader = new MatrixReader();
            return reader.parseFile(filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean write(Matrix matrix, String filepath) {
        try {
            OutputWriter<Matrix> writer = new MatrixWriter();
            return writer.writeToFile(matrix, filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

