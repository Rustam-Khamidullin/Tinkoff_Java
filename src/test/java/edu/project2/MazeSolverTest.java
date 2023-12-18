package edu.project2;

import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.project2.MazeUtils.applySolution;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MazeSolverTest {
    @Test
    public void testDFS() {
        int height = 100;
        int width = 100;
        MazeRenderer mazeRenderer = new MazeRendererKruskal();
        MazeSolver mazeSolver = new MazeSolverDFS();

        Maze maze1 = mazeRenderer.render(height, width);

        assertDoesNotThrow(() -> mazeSolver.solve(maze1));

        assertEquals(Cell.START_END, maze1.getCell(maze1.getStart()));
        assertEquals(Cell.START_END, maze1.getCell(maze1.getEnd()));

        Maze maze2 = mazeRenderer.render(height, width);
        assertDoesNotThrow(() -> mazeSolver.solve(maze2));

        assertEquals(Cell.START_END, maze2.getCell(maze2.getStart()));
        assertEquals(Cell.START_END, maze2.getCell(maze2.getEnd()));
    }

    @Test
    public void testBFS() {
        int height = 50;
        int width = 50;
        MazeRenderer mazeRenderer = new MazeRendererKruskal();
        MazeSolver mazeSolver = new MazeSolverBFS();
        Maze maze = mazeRenderer.render(height, width);

        assertDoesNotThrow(() -> mazeSolver.solve(maze));

        assertEquals(Cell.START_END, maze.getCell(maze.getStart()));
        assertEquals(Cell.START_END, maze.getCell(maze.getEnd()));

        Maze maze2 = mazeRenderer.render(height, width);
        assertDoesNotThrow(() -> mazeSolver.solve(maze2));

        assertEquals(Cell.START_END, maze2.getCell(maze2.getStart()));
        assertEquals(Cell.START_END, maze2.getCell(maze2.getEnd()));
    }

    @Test
    public void testDFSException() {
        int height = 5;
        int width = 5;
        MazeRenderer mazeRenderer = new MazeRendererKruskal();
        MazeSolver mazeSolver = new MazeSolverBFS();
        Maze maze1 = mazeRenderer.render(height, width);

        maze1.setEnd(new Coordinate(0, 0));
        maze1.setStart(new Coordinate(4, 4));

        assertThrows(RuntimeException.class, () -> mazeSolver.solve(maze1));

        Maze maze2 = mazeRenderer.render(height, width);

        maze2.setEnd(new Coordinate(0, 0));
        maze2.setStart(new Coordinate(4, 4));

        assertThrows(RuntimeException.class, () -> mazeSolver.solve(maze2));
    }

    @Test
    public void testBFSException() {
        int height = 50;
        int width = 50;
        MazeRenderer mazeRenderer = new MazeRendererKruskal();
        MazeSolver mazeSolver = new MazeSolverBFS();
        Maze maze = mazeRenderer.render(height, width);

        maze.setEnd(new Coordinate(0, 0));
        maze.setStart(new Coordinate(49, 49));

        assertThrows(RuntimeException.class, () -> mazeSolver.solve(maze));

        Maze maze2 = mazeRenderer.render(height, width);

        maze2.setEnd(new Coordinate(0, 0));
        maze2.setStart(new Coordinate(4, 4));

        assertThrows(RuntimeException.class, () -> mazeSolver.solve(maze2));
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
        MazeSolver mazeSolver = new MazeSolverDFS();

        Cell[][] expected = {
            {Cell.WALL, Cell.START_END, Cell.WALL, Cell.WALL, Cell.WALL,},
            {Cell.WALL, Cell.PATH, Cell.WALL, Cell.PASSAGE, Cell.WALL,},
            {Cell.WALL, Cell.PATH, Cell.PATH, Cell.PATH, Cell.WALL,},
            {Cell.WALL, Cell.PASSAGE, Cell.WALL, Cell.PATH, Cell.WALL,},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.START_END, Cell.WALL,},
        };

        List<Coordinate> solution = mazeSolver.solve(maze);
        applySolution(maze, solution);

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
        MazeSolver mazeSolver = new MazeSolverBFS();

        Cell[][] expected = {
            {Cell.WALL, Cell.START_END, Cell.WALL, Cell.WALL, Cell.WALL,},
            {Cell.WALL, Cell.PATH, Cell.WALL, Cell.PASSAGE, Cell.WALL,},
            {Cell.WALL, Cell.PATH, Cell.PATH, Cell.PATH, Cell.WALL,},
            {Cell.WALL, Cell.PASSAGE, Cell.WALL, Cell.PATH, Cell.WALL,},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.START_END, Cell.WALL,},
        };

        List<Coordinate> solution = mazeSolver.solve(maze);
        applySolution(maze, solution);

        assertEquals(Cell.START_END, maze.getCell(maze.getStart()));
        assertEquals(Cell.START_END, maze.getCell(maze.getEnd()));

        assertArrayEquals(expected, maze.getField());
    }
}
