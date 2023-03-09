package it.itworks.reader;

public class Location {
    private final String filename;
    private final int row;
    private final int col;

    public static Location of(String filename, int row, int col) {
        return new Location(filename, row, col);
    }

    private Location(String filename, int row, int col) {
        this.filename = filename;
        this.row = row;
        this.col = col;
    }

    public String getFilename() {
        return filename;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return  filename + ':' + row + ':' + col;
    }
}
