package edu.hw9.task3;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSolutionMaze {
    private static final String FAILED_SOLUTION = "Failed to solve maze.";

    private ParallelSolutionMaze() {
    }

    public static void solveParallelDFS(Maze maze) {
        Boolean[][] visited = new Boolean[maze.getHeight()][maze.getWeight()];
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWeight(); j++) {
                visited[i][j] = false;
            }
        }

        ParallelDFSTask task = new ParallelDFSTask(maze.getStart(), maze.getEnd(), maze, visited);
        boolean result = new ForkJoinPool().invoke(task);

        if (!result) {
            throw new RuntimeException(FAILED_SOLUTION);
        }

        maze.changeCell(maze.getStart(), Cell.START_END);
        maze.changeCell(maze.getEnd(), Cell.START_END);
    }

    private static class ParallelDFSTask extends RecursiveTask<Boolean> {
        private final Coordinate start;
        private final Coordinate end;
        private final Maze maze;
        private final Boolean[][] visited;

        ParallelDFSTask(Coordinate start, Coordinate end, Maze maze, Boolean[][] visited) {
            this.start = start;
            this.end = end;
            this.maze = maze;
            this.visited = visited;
        }

        @Override
        protected Boolean compute() {
            int x = start.x();
            int y = start.y();
            synchronized (visited) {
                if (visited[x][y]) {
                    return false;
                }

                visited[x][y] = true;
            }
            maze.changeCell(start, Cell.PATH);

            if (start.equals(end)) {
                return true;
            }

            Coordinate[] neighbors = {
                new Coordinate(x - 1, y),
                new Coordinate(x, y - 1),
                new Coordinate(x + 1, y),
                new Coordinate(x, y + 1)
            };

            List<ParallelDFSTask> tasks = new LinkedList<>();
            for (var neighbor : neighbors) {
                var cell = maze.getCell(neighbor);
                if ((cell == Cell.PASSAGE || cell == Cell.START_END) && !visited[neighbor.x()][neighbor.y()]) {
                    ParallelDFSTask task = new ParallelDFSTask(neighbor, end, maze, visited);
                    task.fork();
                    tasks.add(task);
                }
            }

            for (var task : tasks) {
                if (task.join()) {
                    return true;
                }
            }

            maze.changeCell(start, Cell.PASSAGE);
            return false;
        }
    }
}
