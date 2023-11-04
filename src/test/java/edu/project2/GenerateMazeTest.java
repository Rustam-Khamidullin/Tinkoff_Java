package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenerateMazeTest {
    @Test
    public void testGenerateMazeKruskal() {
        int height = 10;
        int width = 10;
        Maze maze = GenerateMaze.mazeKruskal(height, width);
        Cell[][] field = maze.getField();

        assertEquals(height, field.length);
        assertEquals(width, field[0].length);

        assertNotNull(maze.getStart());
        assertNotNull(maze.getEnd());

        int startX = maze.getStart().x();
        int startY = maze.getStart().y();
        int endX = maze.getEnd().x();
        int endY = maze.getEnd().y();
        assertTrue(startX == 0 || startX == height - 1 || startY == 0 || startY == width - 1);
        assertTrue(endX == 0 || endX == height - 1 || endY == 0 || endY == width - 1);
    }

    @Test
    public void testGenerateMazeBacktracking() {
        int height = 10;
        int width = 10;
        Maze maze = GenerateMaze.mazeBacktracking(height, width);
        Cell[][] field = maze.getField();

        assertEquals(height, field.length);
        assertEquals(width, field[0].length);

        assertNotNull(maze.getStart());
        assertNotNull(maze.getEnd());
    }

    @Test
    public void testGenerateMaze() {
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

        assertNotNull(maze.getStart());
        assertNotNull(maze.getEnd());

        int startX = maze.getStart().x();
        int startY = maze.getStart().y();
        int endX = maze.getEnd().x();
        int endY = maze.getEnd().y();
        assertTrue(startX == 0 || startX == height - 1 || startY == 0 || startY == width - 1);
        assertTrue(endX == 0 || endX == height - 1 || endY == 0 || endY == width - 1);
    }
}
