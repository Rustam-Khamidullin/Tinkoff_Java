package edu.project2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MazeSolverBFS implements MazeSolver {
    public MazeSolverBFS() {
    }

    @Override
    public List<Coordinate> solve(Maze maze) {
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
            throw new RuntimeException("Failed to solve maze.");
        }

        List<Coordinate> solution = new ArrayList<>();

        while (!end.equals(maze.getStart())) {
            solution.add(end);
            end = previous[end.x()][end.y()];
        }
        solution.add(maze.getStart());

        return solution;
    }
}
