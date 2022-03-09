package it.itworks.example;

import it.itworks.annotations.OutputCollection;
import it.itworks.writer.OutputWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.List;

public class MatrixWriter extends OutputWriter<Matrix> {

    public MatrixWriter() {
        super(Matrix.class);
    }

    @Override
    public boolean writeToFile(Matrix obj, String outFile) throws Exception {
        List<Field> outputFields = getOutputFields();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            for (Field outField : outputFields) {
                if (outField.isAnnotationPresent(OutputCollection.class)) {
                    // COLLECTION
                    Class<?> elemClazz = outField.getAnnotation(OutputCollection.class).value();
                    if (elemClazz.isAssignableFrom(Antenna.class)) {
                        for(Antenna a: obj.getAntennas()) {
                            writeBasicClass(a, writer);
                        }

                    } else if (elemClazz.isAssignableFrom(Building.class)) {
                        for(Building b: obj.getBuildings()) {
                            writeBasicClass(b, writer);
                        }
                    }
                } else {
                    // BASIC
                    writeBasicField(obj, outField, writer);
                }
            }

            writeLn(writer, "", true);
        }

        return true;
    }
}
