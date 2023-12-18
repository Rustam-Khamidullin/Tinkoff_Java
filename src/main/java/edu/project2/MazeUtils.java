package edu.project2;

import java.util.List;

public class MazeUtils {
    private MazeUtils() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void prettyPrint(Maze maze) {
        for (var i : maze.getField()) {
            for (var j : i) {
                switch (j) {
                    case WALL -> System.out.print("▦");
                    case PASSAGE -> System.out.print("▢");
                    case PATH -> System.out.print("\u001B[31m▢\u001B[0m");
                    default -> System.out.print("\u001B[32m▢\u001B[0m");
                }
            }
            System.out.println();
        }
    }

    public static void applySolution(Maze maze, List<Coordinate> solution) {
        var field = maze.getField();

        for (var coordinate : solution) {
            if (field[coordinate.x()][coordinate.y()] == Cell.WALL) {
                throw new RuntimeException("Incorrect solution.");
            }

            maze.changeCell(coordinate, Cell.PATH);
        }

        maze.changeCell(maze.getStart(), Cell.START_END);
        maze.changeCell(maze.getEnd(), Cell.START_END);
    }
}
