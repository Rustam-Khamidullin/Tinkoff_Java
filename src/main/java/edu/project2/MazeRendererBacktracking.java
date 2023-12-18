package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class MazeRendererBacktracking extends MazeRenderer {
    public MazeRendererBacktracking() {
    }

    private static LinkedList<Coordinate> getUnvisitedNeighbors(Coordinate coordinate, boolean[][] visited, Maze maze) {
        LinkedList<Coordinate> neighbors = new LinkedList<>();

        for (var neighbor : getValidNeighbours(coordinate, maze)) {
            if (!visited[neighbor.x()][neighbor.y()]) {
                neighbors.add(neighbor);
            }
        }

        Collections.shuffle(neighbors);
        return neighbors;
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public Maze render(int height, int width) {
        Maze maze = new Maze(height, width, null, null);
        var field = maze.getField();
        Random random = new Random();
        ArrayList<Coordinate> possibleEnd = new ArrayList<>();

        boolean[][] visited = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze.changeCell(new Coordinate(i, j), Cell.WALL);
                visited[i][j] = false;
            }
        }

        Deque<Coordinate> stack = new LinkedList<>();

        Coordinate start = new Coordinate(random.nextInt(height - 2) + 1, random.nextInt(width - 2) + 1);

        stack.push(start);

        while (!stack.isEmpty()) {
            Coordinate current = stack.peek();
            visited[current.x()][current.y()] = true;
            field[current.x()][current.y()] = Cell.PASSAGE;
            if (!current.equals(start)) {
                possibleEnd.add(current);
            }

            LinkedList<Coordinate> unvisitedNeighbors = getUnvisitedNeighbors(current, visited, maze);
            if (!unvisitedNeighbors.isEmpty()) {
                for (var neighbor : unvisitedNeighbors) {
                    if (getUnvisitedNeighbors(neighbor, visited, maze).size() == 3) {
                        stack.push(neighbor);
                        break;
                    } else {
                        visited[neighbor.x()][neighbor.y()] = true;
                    }
                }
            } else {
                stack.pop();
            }
        }

        maze.setStart(start);
        maze.setEnd(possibleEnd.get(random.nextInt(possibleEnd.size())));

        return maze;
    }
}
