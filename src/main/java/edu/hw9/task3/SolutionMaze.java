package edu.hw9.task3;

import java.util.LinkedList;
import java.util.Queue;

public class SolutionMaze {
    private static final String FAILED_SOLUTION = "Failed to solve maze.";

    private SolutionMaze() {
    }

    public static void solveDFS(Maze maze) {
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWeight()];
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWeight(); j++) {
                visited[i][j] = false;
            }
        }

        if (!dfs(maze.getStart(), maze.getEnd(), maze, visited)) {
            throw new RuntimeException(FAILED_SOLUTION);
        }
        maze.changeCell(maze.getStart(), Cell.START_END);
        maze.changeCell(maze.getEnd(), Cell.START_END);
    }

    private static boolean dfs(Coordinate start, Coordinate end, Maze maze, boolean[][] visited) {
        maze.changeCell(start, Cell.PATH);

        if (start.equals(end)) {
            return true;
        }

        int x = start.x();
        int y = start.y();
        Coordinate[] neighbors =
            {new Coordinate(x - 1, y), new Coordinate(x, y - 1), new Coordinate(x + 1, y), new Coordinate(x, y + 1)};

        for (var neighbor : neighbors) {
            var cell = maze.getCell(neighbor);
            if ((cell == Cell.PASSAGE || cell == Cell.START_END) && !visited[neighbor.x()][neighbor.y()]
                && dfs(neighbor, end, maze, visited)) {
                return true;
            }
        }

        visited[x][y] = true;
        maze.changeCell(start, Cell.PASSAGE);
        return false;
    }

    public static void solveBFS(Maze maze) {
        var end = maze.getEnd();
        boolean solved = false;
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate[][] previous = new Coordinate[maze.getHeight()][maze.getWeight()];
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWeight(); j++) {
                previous[i][j] = null;
            }
        }
        queue.add(maze.getStart());

        while (!queue.isEmpty()) {
            Coordinate curCoordinate = queue.poll();

            if (curCoordinate.equals(end)) {
                solved = true;
                break;
            }

            int x = curCoordinate.x();
            int y = curCoordinate.y();
            Coordinate[] neighbors =
                {new Coordinate(x - 1, y), new Coordinate(x, y - 1), new Coordinate(x + 1, y),
                    new Coordinate(x, y + 1)};

            for (var neighbor : neighbors) {
                var cell = maze.getCell(neighbor);
                if ((cell == Cell.PASSAGE || cell == Cell.START_END) && previous[neighbor.x()][neighbor.y()] == null) {
                    queue.add(neighbor);
                    previous[neighbor.x()][neighbor.y()] = curCoordinate;
                }
            }
        }

        if (!solved) {
            throw new RuntimeException(FAILED_SOLUTION);
        }

        while (!end.equals(maze.getStart())) {
            maze.changeCell(end, Cell.PATH);
            end = previous[end.x()][end.y()];
        }

        maze.changeCell(maze.getStart(), Cell.START_END);
        maze.changeCell(maze.getEnd(), Cell.START_END);
    }
}
