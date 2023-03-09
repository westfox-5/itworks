package it.itworks.reader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import it.itworks.annotations.InputCollection;
import it.itworks.models.Cell;
import it.itworks.models.Matrix;
import it.itworks.models.Snake;

public class MatrixReader extends InputReader<Matrix> {

	public MatrixReader() {
		super(Matrix.class);
	}

	@Override
	public Matrix parseFile(String filePath) throws Exception {
		final Matrix matrix = new Matrix();	
		
        final CustomReader reader = new CustomReader(filePath, ' ');
        List<Field> inputFields = getInputFields();
        for (Field inField : inputFields) {
            if (inField.isAnnotationPresent(InputCollection.class)) {
                // COLLECTION
                Class<?> elemClazz = inField.getAnnotation(InputCollection.class).value();

                if (elemClazz.isAssignableFrom(Snake.class)) {
                    // demons
                    List<Snake> list = new ArrayList<>();

                    assert (matrix.getS() != null);

                    for (int i = 0; i < matrix.getS(); ++i) {
                        Snake s = parseSnake(reader);
                        list.add(s);
                    }

                    set(matrix, inField, list);
                } else if (elemClazz.isAssignableFrom(Cell.class)) {
                	Cell[][] celle = new Cell[matrix.getR()][matrix.getC()];
                	
                	for (int r = 0; r < matrix.getR(); ++r) {
                		for (int c = 0; c < matrix.getC(); ++c) {
                			Cell cell = parseCell(matrix, r, c, reader);
                			celle[r][c] = cell;
                		}
                	}
                	
                	matrix.setCelle(celle);
                }
                
                
            } else {
                // BASIC
                parseBasicField(matrix, inField, reader);
            }
        }

        reader.close();
        
        return matrix;
	}
	
	
	private Snake parseSnake(final CustomReader reader) throws Exception {
        Snake s = new Snake();

        List<Field> inputFields = getInputFields(Snake.class);
        for (Field inField : inputFields) {
        	parseBasicField(s, inField, reader);
        }
        return s;
    };
    
    private Cell parseCell(Matrix matrix, int r, int c, final CustomReader reader) throws Exception {
    	Cell cell = new Cell(matrix, r, c);

        List<Field> inputFields = getInputFields(Cell.class);
        for (Field inField : inputFields) {
        	Object val = getBasicField(c, inField, reader);
        	if (val != null && val.equals("*")) {
        		cell.setWh(true);
        	} else if (val != null && val instanceof Integer) {
        		cell.setValue((Integer)val);
        	}
        }
        
        return cell;
    };

}
