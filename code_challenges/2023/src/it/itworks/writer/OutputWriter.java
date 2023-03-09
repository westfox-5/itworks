package it.itworks.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import it.itworks.annotations.Input;
import it.itworks.annotations.Output;
import it.itworks.annotations.OutputClass;

public abstract class OutputWriter<T> {
   private final Class<T> clazz;

    public OutputWriter(Class<T> clazz) {
        if (!clazz.isAnnotationPresent(OutputClass.class)) {
            throw new RuntimeException("Invalid class parameter!");
        }

        this.clazz = clazz;
    }

    public abstract boolean writeToFile(T obj, String outFile) throws Exception;

    protected List<Field> getOutputFields() {
        return getOutputFields(this.clazz);
    }

    // flush on newLine
    protected void writeLn(BufferedWriter writer, String str, boolean newLine) throws IOException {
        writer.write( str + (newLine?"\n":" ") );
        if (newLine) writer.flush();
    }

    protected <V> void writeBasicClass(final V obj, final BufferedWriter writer) throws IOException, IllegalAccessException {
        List<Field> outFields = getOutputFields(obj.getClass());
        for (Field outField : outFields) {
            writeBasicField(obj, outField, writer);
        }
    }

    protected <V> void writeBasicField(V obj, final Field field, final BufferedWriter writer) throws IOException, IllegalAccessException {
        Output annotation = field.getAnnotation(Output.class);
        Object value = get(obj, field);
        writeLn(writer, String.valueOf(value), annotation.newLine());
    }

    protected <V> Object get(V obj, Field field) throws IllegalAccessException {
        boolean canAccess = field.canAccess(obj);
        field.setAccessible(true);
        Object res = field.get(obj);
        field.setAccessible(canAccess);
        return res;
    }

    protected static List<Field> getOutputFields(Class<?> clz) {
        if (clz == null) {
            return new ArrayList<>();
        }
        List<Field> fields = Arrays.stream(clz.getDeclaredFields())
                .filter(f -> /* Modifier.isPublic(f) &&*/ f.isAnnotationPresent(Output.class)) // input fields only
                .collect(Collectors.toList());

        fields.addAll(getOutputFields(clz.getSuperclass()));
        fields.sort(Comparator.comparingInt(f -> f.getAnnotation(Input.class).position()));
        return fields;
    }
}
