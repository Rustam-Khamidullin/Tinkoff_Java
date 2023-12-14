package edu.hw9;

import edu.hw9.task3.GenerateMaze;
import edu.hw9.task3.ParallelSolutionMaze;
import edu.hw9.task3.SolutionMaze;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    public void parallelSolutionTest() {
        var maze = GenerateMaze.mazeKruskal(200, 200);

        SolutionMaze.solveDFS(maze);

        maze.prettyPrint();
    }
}
