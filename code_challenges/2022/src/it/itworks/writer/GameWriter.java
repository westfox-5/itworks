package it.itworks.writer;

import it.itworks.annotations.OutputCollection;
import it.itworks.example.Antenna;
import it.itworks.example.Building;
import it.itworks.example.Matrix;
import it.itworks.models.Demon;
import it.itworks.models.Game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.List;

public class GameWriter extends OutputWriter<Game> {

    public GameWriter() {
        super(Game.class);
    }

    @Override
    public boolean writeToFile(Game obj, String outFile) throws Exception {
        List<Field> outputFields = getOutputFields();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            for (Field outField : outputFields) {
                if (outField.isAnnotationPresent(OutputCollection.class)) {
                    // COLLECTION
                    Class<?> elemClazz = outField.getAnnotation(OutputCollection.class).value();
                    if (elemClazz.isAssignableFrom(Demon.class)) {
                        for (Demon a : obj.getDefeatedDemons()) {
                            writeBasicClass(a, writer);
                        }
                    }
                } else {
                    // BASIC
                    writeBasicField(obj, outField, writer);
                }
            }
        }

        return true;
    }
}
