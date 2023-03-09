package it.itworks.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import it.itworks.annotations.OutputCollection;
import it.itworks.models.Cell;
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
                        for (Snake s : obj.getSnakes()) {
                        	
                        	writeSnake(s, writer);
                            
                        	writeLn(writer, "", true);
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

	private void writeSnake(Snake s, BufferedWriter writer) throws IllegalAccessException, IOException {		
		List<Field> outputFields = getOutputFields(Snake.class);
		 for (Field outField : outputFields) {
             if (outField.isAnnotationPresent(OutputCollection.class)) {
                 // COLLECTION
                 Class<?> elemClazz = outField.getAnnotation(OutputCollection.class).value();
                 if (elemClazz.isAssignableFrom(Cell.class)) {
                	 
                	 boolean f = true;
                     for (Cell c: s.getPath()) {
                    	 if (f) {
                    		 f = false; 
                    		 continue;
                    	 }
                    	 
                    	 if (c.isWormhole()) {
                    		 // TODO
                    	 } else {
                    		 
                    		 writeLn(writer, c.getDir(), false);
                    	 }
                    	 
                     }
                    
                 }
             } else {
                 // BASIC
                 writeBasicField(s, outField, writer);
             }
         }
	}
}
