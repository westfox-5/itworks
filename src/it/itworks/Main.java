package it.itworks;

import it.itworks.models.Demon;
import it.itworks.models.Fragment;
import it.itworks.models.Game;
import it.itworks.reader.GameReader;
import it.itworks.reader.InputReader;

public class Main {
    public static void main(String[] args) {
        String filename = "00-example.txt";
        String inDir = "tests/";
        String outDir = "outputs/";

        System.out.println("Reading input file '"+inDir+filename+"'...");
        Game game = read(inDir+filename);
        if (game == null) {
            throw new RuntimeException("Failed to parse input file.");
        }
        System.out.println("Success!");

        /*
        System.out.println("-------------------------");
        System.out.println("stamina: "+ game.getStamina());
        System.out.println("maxStamina: "+ game.getMaxStamina());
        System.out.println("maxTurns: "+ game.getMaxTurns());

        System.out.println("numDemons: "+ game.getNumDemons());
        System.out.println("Demons: ");
        for (Demon d: game.getDemons()) {
            System.out.print(d.getId() + ":: " +d.getStaminaForKill() + " " + d.getTurnToRecoverStamina() + " " + d.getStaminaRecovered() + " " + d.getNumberOfFragments() + " ");
            for (Fragment f: d.getFragmentsForTurn()) {
                System.out.print(f.getFragment() + " ");
            }
            System.out.println();
        }
        */


    }

    private static Game read(String filepath) {
        try {
            InputReader<Game> reader = new GameReader();
            return reader.parseFile(filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
/*
    private static boolean write(Matrix matrix, String filepath) {
        try {
            OutputWriter<Matrix> writer = new MatrixWriter();
            return writer.writeToFile(matrix, filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    */
}

