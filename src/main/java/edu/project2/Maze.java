package edu.project2;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] field;
    private Coordinate start;
    private Coordinate end;

    public Maze(int height, int weight, Coordinate start, Coordinate end) {
        this(height, weight, new Cell[height][weight], start, end);
    }

    @SuppressWarnings("MagicNumber")
    public Maze(int height, int weight, Cell[][] field, Coordinate start, Coordinate end) {
        if (height < 3 || weight < 3) {
            throw new RuntimeException("Height, weight >= 3.");
        }
        this.height = height;
        this.width = weight;
        this.start = start;
        this.end = end;
        this.field = field;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return width;
    }

    public Coordinate getStart() {
        return start;
    }

    public void setStart(Coordinate coordinate) {
        if (invalidCoordinate(coordinate) || coordinate.equals(start) || coordinate.equals(end)) {
            return;
        }

        field[coordinate.x()][coordinate.y()] = Cell.START_END;
        start = coordinate;
    }

    public Coordinate getEnd() {
        return end;
    }

    public void setEnd(Coordinate coordinate) {
        if (invalidCoordinate(coordinate) || coordinate.equals(start) || coordinate.equals(end)) {
            return;
        }

        field[coordinate.x()][coordinate.y()] = Cell.START_END;
        end = coordinate;
    }

    public Cell getCell(Coordinate coordinate) {
        if (invalidCoordinate(coordinate)) {
            return null;
        }
        return field[coordinate.x()][coordinate.y()];
    }

    public void changeCell(Coordinate coordinate, Cell cell) {
        if (invalidCoordinate(coordinate)) {
            return;
        }
        field[coordinate.x()][coordinate.y()] = cell;
    }

    public Cell[][] getField() {
        return field;
    }

    public boolean invalidCoordinate(Coordinate coordinate) {
        return coordinate.x() < 0 || coordinate.x() >= height || coordinate.y() < 0 || coordinate.y() >= width;
    }
}
