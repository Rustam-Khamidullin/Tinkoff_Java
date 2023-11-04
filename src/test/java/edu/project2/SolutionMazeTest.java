package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SolutionMazeTest {
    @Test
    public void testDFS() {
        int height = 100;
        int width = 100;
        Maze maze1 = GenerateMaze.mazeKruskal(height, width);

        assertDoesNotThrow(() -> SolutionMaze.solveDFS(maze1));

        assertEquals(Cell.START_END, maze1.getCell(maze1.getStart()));
        assertEquals(Cell.START_END, maze1.getCell(maze1.getEnd()));

        Maze maze2 = GenerateMaze.mazeBacktracking(height, width);
        assertDoesNotThrow(() -> SolutionMaze.solveDFS(maze2));

        assertEquals(Cell.START_END, maze2.getCell(maze2.getStart()));
        assertEquals(Cell.START_END, maze2.getCell(maze2.getEnd()));
    }

    @Test
    public void testBFS() {
        int height = 50;
        int width = 50;
        Maze maze = GenerateMaze.mazeKruskal(height, width);

        assertDoesNotThrow(() -> SolutionMaze.solveBFS(maze));

        assertEquals(Cell.START_END, maze.getCell(maze.getStart()));
        assertEquals(Cell.START_END, maze.getCell(maze.getEnd()));

        Maze maze2 = GenerateMaze.mazeBacktracking(height, width);
        assertDoesNotThrow(() -> SolutionMaze.solveBFS(maze2));

        assertEquals(Cell.START_END, maze2.getCell(maze2.getStart()));
        assertEquals(Cell.START_END, maze2.getCell(maze2.getEnd()));
    }

    @Test
    public void testDFSException() {
        int height = 5;
        int width = 5;
        Maze maze1 = GenerateMaze.mazeKruskal(height, width);

        maze1.setEnd(new Coordinate(0, 0));
        maze1.setStart(new Coordinate(4, 4));

        assertThrows(RuntimeException.class, () -> SolutionMaze.solveDFS(maze1));

        Maze maze2 = GenerateMaze.mazeBacktracking(height, width);

        maze2.setEnd(new Coordinate(0, 0));
        maze2.setStart(new Coordinate(4, 4));

        assertThrows(RuntimeException.class, () -> SolutionMaze.solveDFS(maze2));
    }

    @Test
    public void testBFSException() {
        int height = 50;
        int width = 50;
        Maze maze = GenerateMaze.mazeKruskal(height, width);

        maze.setEnd(new Coordinate(0, 0));
        maze.setStart(new Coordinate(49, 49));

        assertThrows(RuntimeException.class, () -> SolutionMaze.solveBFS(maze));

        Maze maze2 = GenerateMaze.mazeBacktracking(height, width);

        maze2.setEnd(new Coordinate(0, 0));
        maze2.setStart(new Coordinate(4, 4));

        assertThrows(RuntimeException.class, () -> SolutionMaze.solveBFS(maze2));
    }

    @Test
    public void testDFSMyField() {
        int height = 5;
        int width = 5;
        Cell[][] field = {
            {Cell.WALL, Cell.START_END, Cell.WALL, Cell.WALL, Cell.WALL,},
            {Cell.WALL, Cell.PASSAGE, Cell.WALL, Cell.PASSAGE, Cell.WALL,},
            {Cell.WALL, Cell.PASSAGE, Cell.PASSAGE, Cell.PASSAGE, Cell.WALL,},
            {Cell.WALL, Cell.PASSAGE, Cell.WALL, Cell.PASSAGE, Cell.WALL,},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.START_END, Cell.WALL,},
        };
        Maze maze = new Maze(height, width, field, new Coordinate(0, 1), new Coordinate(4, 3));

        Cell[][] expected = {
            {Cell.WALL, Cell.START_END, Cell.WALL, Cell.WALL, Cell.WALL,},
            {Cell.WALL, Cell.PATH, Cell.WALL, Cell.PASSAGE, Cell.WALL,},
            {Cell.WALL, Cell.PATH, Cell.PATH, Cell.PATH, Cell.WALL,},
            {Cell.WALL, Cell.PASSAGE, Cell.WALL, Cell.PATH, Cell.WALL,},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.START_END, Cell.WALL,},
        };

        assertDoesNotThrow(() -> SolutionMaze.solveDFS(maze));

        assertEquals(Cell.START_END, maze.getCell(maze.getStart()));
        assertEquals(Cell.START_END, maze.getCell(maze.getEnd()));

        assertArrayEquals(expected, maze.getField());
    }

    @Test
    public void testBFSMyField() {
        int height = 5;
        int width = 5;
        Cell[][] field = {
            {Cell.WALL, Cell.START_END, Cell.WALL, Cell.WALL, Cell.WALL,},
            {Cell.WALL, Cell.PASSAGE, Cell.WALL, Cell.PASSAGE, Cell.WALL,},
            {Cell.WALL, Cell.PASSAGE, Cell.PASSAGE, Cell.PASSAGE, Cell.WALL,},
            {Cell.WALL, Cell.PASSAGE, Cell.WALL, Cell.PASSAGE, Cell.WALL,},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.START_END, Cell.WALL,},
        };
        Maze maze = new Maze(height, width, field, new Coordinate(0, 1), new Coordinate(4, 3));

        Cell[][] expected = {
            {Cell.WALL, Cell.START_END, Cell.WALL, Cell.WALL, Cell.WALL,},
            {Cell.WALL, Cell.PATH, Cell.WALL, Cell.PASSAGE, Cell.WALL,},
            {Cell.WALL, Cell.PATH, Cell.PATH, Cell.PATH, Cell.WALL,},
            {Cell.WALL, Cell.PASSAGE, Cell.WALL, Cell.PATH, Cell.WALL,},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.START_END, Cell.WALL,},
        };

        assertDoesNotThrow(() -> SolutionMaze.solveBFS(maze));

        assertEquals(Cell.START_END, maze.getCell(maze.getStart()));
        assertEquals(Cell.START_END, maze.getCell(maze.getEnd()));

        assertArrayEquals(expected, maze.getField());
    }
}
