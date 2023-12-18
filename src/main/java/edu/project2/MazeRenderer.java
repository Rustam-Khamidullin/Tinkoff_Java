package edu.project2;

import java.util.LinkedList;

public abstract class MazeRenderer {
    abstract Maze render(int height, int width);

    protected static LinkedList<Coordinate> getValidNeighbours(Coordinate coordinate, Maze maze) {
        int x = coordinate.x();
        int y = coordinate.y();

        var result = new LinkedList<Coordinate>();
        if (!maze.invalidCoordinate(new Coordinate(x - 1, y))) {
            result.add(new Coordinate(x - 1, y));
        }
        if (!maze.invalidCoordinate(new Coordinate(x + 1, y))) {
            result.add(new Coordinate(x + 1, y));
        }
        if (!maze.invalidCoordinate(new Coordinate(x, y - 1))) {
            result.add(new Coordinate(x, y - 1));
        }
        if (!maze.invalidCoordinate(new Coordinate(x, y + 1))) {
            result.add(new Coordinate(x, y + 1));
        }
        return result;
    }
}
