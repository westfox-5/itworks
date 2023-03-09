package it.itworks.reader;

import it.itworks.annotations.InputCollection;
import it.itworks.models.Demon;
import it.itworks.models.Fragment;
import it.itworks.models.Game;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GameReader extends InputReader<Game> {

    public GameReader() {
        super(Game.class);
    }

    @Override
    public Game parseFile(String filepath) throws Exception {
        final Game game = new Game();

        final CustomReader reader = new CustomReader(filepath, ' ');

        List<Field> inputFields = getInputFields();
        for (Field inField : inputFields) {
            if (inField.isAnnotationPresent(InputCollection.class)) {
                // COLLECTION
                Class<?> elemClazz = inField.getAnnotation(InputCollection.class).value();

                if (elemClazz.isAssignableFrom(Demon.class)) {
                    // demons
                    List<Demon> list = new ArrayList<>();

                    assert (game.getNumDemons() != null);

                    for (int i = 0; i < game.getNumDemons(); ++i) {
                        Demon e = parseDemon(reader);
                        list.add(e);
                    }

                    set(game, inField, list);
                }
            } else {
                // BASIC
                parseBasicField(game, inField, reader);
            }
        }
        
        
        // CREAZIONE R*C
        matrix.create();
        

        reader.close();

        return game;
    }

    private Demon parseDemon(final CustomReader reader) throws Exception {
        Demon demon = new Demon();

        List<Field> inputFields = getInputFields(Demon.class);
        for (Field inField : inputFields) {
            if (inField.isAnnotationPresent(InputCollection.class)) {
                // COLLECTION
                Class<?> elemClazz = inField.getAnnotation(InputCollection.class).value();

                if (elemClazz.isAssignableFrom(Fragment.class)) {
                    // demons
                    List<Fragment> list = new ArrayList<>();

                    assert (demon.getNumberOfFragments() != null);

                    if ( demon.getNumberOfFragments() == 0) {
                        reader.getNextTokenStr(); // consumo lo spazio
                    } else {
                        for (int i = 0; i < demon.getNumberOfFragments(); ++i) {
                            Fragment fragment = parseBasicClass(Fragment.class, reader);
                            list.add(fragment);
                        }
                    }
                    set(demon, inField, list);
                }
            } else {
                // BASIC
                parseBasicField(demon, inField, reader);
            }
        }
        return demon;
    }
}
