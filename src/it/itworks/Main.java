package it.itworks;

import it.itworks.models.Demon;
import it.itworks.models.Fragment;
import it.itworks.models.Game;
import it.itworks.reader.GameReader;
import it.itworks.reader.InputReader;
import it.itworks.solution.*;
import it.itworks.writer.GameWriter;
import it.itworks.writer.OutputWriter;

public class Main {
    public static void main(String[] args) {
        Game game = read(args[0]);
        if (game == null) {
            throw new RuntimeException("Failed to parse input file.");
        }

        new Solution4(1.0, 0.9, 1.2, 1.2).solve(game);

        write(game, args[1]);
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

