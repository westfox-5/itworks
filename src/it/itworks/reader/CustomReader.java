package it.itworks.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CustomReader {
    private String path;
    private char lineSeparator;
    private boolean lastNewLine;

    private boolean init;
    private Reader reader;
    private char[] buffer;
    private final int bufferSize = 10;
    private int pos;
    private int nPos;

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

    public String getNextToken() throws IOException {
        init();

        this.lastNewLine = false;
        StringBuilder sb = new StringBuilder();

        Character data;
        boolean lineFeed = false;

        while ( (data = getNextChar()) != null ) {
            if ( data == '\n' ) {
                this.lastNewLine = true;
                break;
            } else if ( data == '\r' ) {
                lineFeed = true;
                this.lastNewLine = true;
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
                getNextChar(); // consumo '\r'
            }
        }

        return sb.toString();
    }

    public void close() {
        if ( reader != null ) {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    public boolean isLastNewLine() {
        return lastNewLine;
    }
}
