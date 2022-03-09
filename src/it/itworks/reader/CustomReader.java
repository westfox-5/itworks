package it.itworks.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;

public class CustomReader {
    private final String path;
    private final char lineSeparator;
    private boolean lastNewLine;

    private boolean init;
    private Reader reader;
    private char[] buffer;
    private final int bufferSize = 10;
    private int pos;
    private int nPos;

    private String filename;
    private int row, begin_col, end_col;

    public CustomReader(String path, char lineSeparator) {
        this.path = path;
        this.lineSeparator = lineSeparator;
        this.init = false;
        this.lastNewLine = false;
    }

    private void init() throws FileNotFoundException {
        if ( !init ) {
            // inputStream = new FileInputStream(path);
            reader = new FileReader(path);
            buffer = new char[bufferSize];
            pos = 0;
            nPos = 0;

            filename = Path.of(path).getFileName().toString();
            row = 0;
            begin_col = 0;
            end_col = 0;

            init = true;
        }
    }

    private Character getNextChar() throws IOException {
        if ( pos >= nPos ) {
            pos = 0;
            nPos = 0;

            int r = reader.read(buffer, 0, bufferSize);
            if ( r >= 0 ) {
                nPos = r;
            }
        }

        if ( hasNext() ) {
            return buffer[pos++];
        }

        return null;
    }

    private boolean hasNext() {
        return pos < nPos;
    }

    private char peekNextChar() {
        return buffer[pos];
    }

    public String getNextTokenStr() throws IOException {
        init();

        this.lastNewLine = false;
        StringBuilder sb = new StringBuilder();

        Character data;
        boolean lineFeed = false;

        begin_col = end_col + 1;
        while ( (data = getNextChar()) != null ) {
            end_col++;
            if ( data == '\n' ) {
                this.lastNewLine = true;
                end_col = 0;
                row++;
                break;
            } else if ( data == '\r' ) {
                lineFeed = true;
                this.lastNewLine = true;
                end_col=0;
                row++;
                break;
            } else if ( data == lineSeparator ) {
                break;
            }

            // String s = new String(data, StandardCharsets.UTF_8);
            sb.append(data);
        }

        if ( data == null && sb.toString().length() == 0 ) {
            throw new RuntimeException("EOF");
        }

        if ( lineFeed ) {
            data = peekNextChar();
            if ( data == '\n' ) {
                getNextChar(); // consumo '\r's
            }
        }

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public <T> Token<T> getNextToken(Class<T> clazz) throws IOException {
        String nextValue = getNextTokenStr();
        Location loc = Location.of(filename, row, begin_col);

        if (clazz == Double.class) {
            return (Token<T>) Token.ofDouble(loc, Double.parseDouble(nextValue));
        }

        if (clazz == Integer.class) {
            return (Token<T>) Token.ofInteger(loc, Integer.parseInt(nextValue));
        }

        if (clazz == String.class) {
            return (Token<T>) Token.ofString(loc, nextValue);
        }

        throw new RuntimeException("Could not cast token to " + clazz.getName() + " type.");
    }

    public void close() {
        if ( reader != null ) {
            try {
                reader.close();
            } catch (IOException ignored) {
            }
        }
    }

    public boolean isLastNewLine() {
        return lastNewLine;
    }
}
