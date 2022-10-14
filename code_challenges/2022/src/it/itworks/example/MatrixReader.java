package it.itworks.example;

import it.itworks.annotations.InputCollection;
import it.itworks.reader.CustomReader;
import it.itworks.reader.InputReader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class MatrixReader extends InputReader<Matrix> {

    public MatrixReader() {
        super(Matrix.class);
    }

    @Override
    public Matrix parseFile(String filepath) throws Exception {
        final Matrix matrix = new Matrix();

        final CustomReader reader = new CustomReader(filepath, ' ');

        List<Field> inputFields = getInputFields();
        for (Field inField : inputFields) {
            if (inField.isAnnotationPresent(InputCollection.class)) {
                // COLLECTION
                Class<?> elemClazz = inField.getAnnotation(InputCollection.class).value();

                if (elemClazz.isAssignableFrom(Antenna.class)) {
                    // elements
                    List<Antenna> list = new ArrayList<>();

                    assert (matrix.getNumAntennas() != null);

                    for (int i = 0; i < matrix.getNumAntennas(); ++i) {
                        Antenna e = parseBasicClass(Antenna.class, reader);
                        list.add(e);
                    }

                    set(matrix, inField, list);
                } else if (elemClazz.isAssignableFrom(Building.class)) {
                    // buildings
                    List<Building> list = new ArrayList<>();

                    assert (matrix.getNumBuildings() != null);

                    for (int i = 0; i < matrix.getNumBuildings(); ++i) {
                        Building e = parseBasicClass(Building.class, reader);
                        list.add(e);
                    }

                    set(matrix, inField, list);
                }
            } else {
                // BASIC
                parseBasicField(matrix, inField, reader);
            }
        }

        reader.close();

        return matrix;
    }
}
