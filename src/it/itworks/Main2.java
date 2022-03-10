package it.itworks;

import it.itworks.models.Demon;
import it.itworks.models.Fragment;
import it.itworks.models.Game;
import it.itworks.reader.GameReader;
import it.itworks.reader.InputReader;
import it.itworks.solution.*;
import it.itworks.writer.GameWriter;
import it.itworks.writer.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main2 {
    public static double max_smul = 0;
    public static double max_skmul = 0;
    public static double max_srmul = 0;
    public static double max_trmul = 0;
    public static double max_score = 0;

    public static void main(String[] args) {
        String filename = "01-the-cloud-abyss.txt";
        String inDir = "tests/";
        String outDir = "outputs/";
        String input = inDir + filename;

        Game game = read(input);
        if (game == null) {
            throw new RuntimeException("Failed to parse input file.");
        }

        solve(game.clone(), new Solution1());
        solve(game.clone(), new Solution2());
        solve(game.clone(), new Solution3());

        // solve(game.clone(), new Solution4());

        /*ExecutorService executorService =  new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());*/
/*
        //List<Double> range = List.of(0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3, 1.4, 1.5);
        List<Double> range = List.of(0.8, 0.9, 1.0, 1.1, 1.2, 1.3);
        for ( Double smul : range ) {
            Solution4.S_MUL = smul;

            for ( Double skmul : range ) {
                Solution4.SK_MUL = skmul;

                for ( Double srmul : range ) {
                    Solution4.SR_MUL = srmul;

                    for ( Double trmul : range ) {
                        Solution4.TR_MUL = trmul;

                        Game newGame = game.clone();
                        solve(newGame, new Solution4());

                        if ( newGame.getTotalFragments() > max_score ) {
                            max_smul = smul;
                            max_skmul = skmul;
                            max_srmul = srmul;
                            max_trmul = trmul;

                            max_score = newGame.getTotalFragments();
                        }

                        //Solution4Thread t = new Solution4Thread(game, smul, skmul, srmul, trmul);
                        // callableTasks.add(t);
                        //executorService.execute(t);
                    }
                }
            }
        }
/*
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800000000, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
**/
        System.out.println("MAX SCORE: " + max_score + " [ smul: "+ max_smul + ", skmul: "+ max_skmul + ", srmul: " + max_srmul + ", trmul: "+ max_trmul+ "]");



//      write(game, outDir + filename);

    }


    public static void setMaxGame(Game game, double smul, double skmul, double srmul, double trmul) {
        synchronized (Object.class) {
            if ( game.getMaxScore() > max_score ) {
                max_smul = smul;
                max_skmul = skmul;
                max_srmul = srmul;
                max_trmul = trmul;
            }
        }
    }


    private static Game solve(Game game, Solution solver) {
        //System.out.println("START " + solver.getClass().getCanonicalName());

        solver.solve(game);
       //System.out.println("END " + solver.getClass().getSimpleName() + ": " + game.getTotalFragments());
        //System.out.println();

        return game;
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


    private static boolean write(Game game, String filepath) {
        try {
            OutputWriter<Game> writer = new GameWriter();
            return writer.writeToFile(game, filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

