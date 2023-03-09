package it.itworks.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.List;

import it.itworks.annotations.OutputCollection;
import it.itworks.models.Matrix;
import it.itworks.models.Snake;

public class MatrixWriter extends OutputWriter<Matrix> {

	public MatrixWriter() {
		super(Matrix.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean writeToFile(Matrix obj, String outFile) throws Exception {
		 List<Field> outputFields = getOutputFields();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            for (Field outField : outputFields) {
                if (outField.isAnnotationPresent(OutputCollection.class)) {
                    // COLLECTION
                    Class<?> elemClazz = outField.getAnnotation(OutputCollection.class).value();
                    if (elemClazz.isAssignableFrom(Snake.class)) {
                        for (Snake s : obj.getOutputSnakes()) {
                            writeBasicClass(s, writer);
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
