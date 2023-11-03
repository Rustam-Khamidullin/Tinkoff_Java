package edu.project2;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] maze;
    private Coordinate start;
    private Coordinate end;

    public Maze(int height, int weight, Coordinate start, Coordinate end) {
        if (height < 3 || weight < 3) {
            throw new RuntimeException("Height, weight >= 3.");
        }
        this.height = height;
        this.width = weight;
        this.start = start;
        this.end = end;
        maze = new Cell[height][weight];
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
        if (maze[coordinate.x()][coordinate.y()] == Cell.PASSAGE) {
            start = coordinate;
        }
    }

    public Coordinate getEnd() {
        return end;
    }

    public void setEnd(Coordinate coordinate) {
        if (maze[coordinate.x()][coordinate.y()] == Cell.PASSAGE) {
            end = coordinate;
        }
    }

    public Cell[][] getMaze() {
        return maze;
    }
}
