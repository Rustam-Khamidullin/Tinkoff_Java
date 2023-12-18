package edu.project2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MazeSolverDFS implements MazeSolver {
    public MazeSolverDFS() {
    }

    private static boolean dfs(Coordinate start, Coordinate end, Maze maze, boolean[][] visited, Set<Coordinate> path) {
        path.add(start);

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
                && !path.contains(neighbor) && dfs(neighbor, end, maze, visited, path)) {
                return true;
            }
        }

        visited[x][y] = true;
        path.remove(start);
        return false;
    }

    @Override
    public List<Coordinate> solve(Maze maze) {
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWeight()];
        Set<Coordinate> path = new HashSet<>();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWeight(); j++) {
                visited[i][j] = false;
            }
        }

        boolean isSolved = dfs(maze.getStart(), maze.getEnd(), maze, visited, path);

        if (!isSolved) {
            throw new RuntimeException("Failed to solve maze.");
        }

        return  new ArrayList<>(path);
    }
}
