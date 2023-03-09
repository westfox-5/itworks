package it.itworks.reader;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;

public abstract class InputReader<T> {
    private final Class<T> clazz;

    protected InputReader(Class<T> clazz) {
        if (!clazz.isAnnotationPresent(InputClass.class)) {
            throw new RuntimeException("Invalid class parameter!");
        }

        this.clazz = clazz;
    }

    public abstract T parseFile(String filePath) throws Exception;

    protected List<Field> getInputFields() {
        return getInputFields(clazz);
    }

    protected <V> V parseBasicClass(final Class<V> clazz, final CustomReader reader) throws Exception {
        final V obj = clazz.getDeclaredConstructor().newInstance();

        List<Field> inputFields = getInputFields(clazz);
        for (Field inField: inputFields) {
            parseBasicField(obj, inField, reader);
        }
        return obj;
    }
    
    protected <V> Object getBasicField(V obj, Field field, final CustomReader reader) throws IOException, IllegalAccessException {
        Class<?> inType = field.getType();
        Token<?> token = reader.getNextToken(inType);

        if (validate(field, token)) {
        	return token.getValue();
        }
        return null;
    }

    protected <V> void parseBasicField(V obj, Field field, final CustomReader reader) throws IOException, IllegalAccessException {
    	Object o = getBasicField(obj, field, reader);
    	if (o != null) {
    		set(obj, field, o);
    	}
    }

    protected <V> void set(V obj, Field field, Object value) throws IllegalAccessException {
        boolean canAccess = field.canAccess(obj);
        field.setAccessible(true);
        field.set(obj, value);
        field.setAccessible(canAccess);
    }

    private <V> boolean validate(Field field, Token<V> token) {
        Input annotation = field.getAnnotation(Input.class);

        Location loc = token.getLocation();
        V value = token.getValue();
        if (value == null) {
            throw new RuntimeException(loc + ": ERROR: no value was read.");
        }

        int max = annotation.max();
        if (max > -1) {
            boolean maxOk = false;

            if (value instanceof Integer) {
                maxOk = (Integer) value < max;
            }
            if (value instanceof Double) {
                maxOk = (Double) value < max;
            }

            if (!maxOk && !(value instanceof String)) {
                throw new RuntimeException(loc +
                        ": VALIDATION ERROR" +
                        ": field: " + field.getName() +
                        ": Expecting MAX value " + max + " but got " + value + " instead.");
            }
        }

        int min = annotation.min();
        if (min > -1) {
            boolean minOk = false;

            if (value instanceof Integer) {
                minOk = (Integer) value >= min;
            }
            if (value instanceof Double) {
                minOk = (Double) value >= min;
            }

            if (!minOk && !(value instanceof String)) {
                throw new RuntimeException(loc +
                        ": VALIDATION ERROR" +
                        ": field: " + field.getName() +
                        ": Expecting MIN value " + min + " but got " + value + " instead.");
            }
        }

        return true;
    }

    protected static List<Field> getInputFields(Class<?> clz) {
        if (clz == null) {
            return new ArrayList<>();
        }
        List<Field> fields = Arrays.stream(clz.getDeclaredFields())
                .filter(f -> /* Modifier.isPublic(f) &&*/ f.isAnnotationPresent(Input.class)) // input fields only
                .collect(Collectors.toList());
        fields.addAll(getInputFields(clz.getSuperclass()));
        fields.sort(Comparator.comparingInt(f -> f.getAnnotation(Input.class).position()));
        return fields;
    }
}
