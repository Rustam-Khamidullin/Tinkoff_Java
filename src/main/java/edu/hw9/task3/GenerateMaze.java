package edu.hw9.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class GenerateMaze {
    private GenerateMaze() {
    }

    public static Maze mazeKruskal(int height, int width) {
        var maze = new Maze(height, width, null, null);
        var field = maze.getField();
        int groupId = 1;
        var groups = new int[height][width];
        var randomCoordinates = new ArrayList<Coordinate>();
        ArrayList<Coordinate> startEnd = new ArrayList<>(2);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = Cell.WALL;
                if (inBox(i, j, height, width)) {
                    randomCoordinates.add(new Coordinate(i, j));
                }
            }
        }

        Collections.shuffle(randomCoordinates);

        for (var coordinate : randomCoordinates) {
            int x = coordinate.x();
            int y = coordinate.y();

            HashSet<Integer> tmp = new HashSet<>();
            int setId = 0;
            boolean cycle = false;
            var neighbors = getValidNeighbours(coordinate, maze);

            for (var neighbor : neighbors) {
                var neighborGroup = groups[neighbor.x()][neighbor.y()];
                if (neighborGroup != 0) {
                    tmp.add(neighborGroup);
                    if (setId == neighborGroup) {
                        cycle = true;
                    }
                    setId = neighborGroup;
                }
            }
            if (cycle) {
                continue;
            }
            if (tmp.isEmpty()) {
                groups[x][y] = groupId;
                groupId++;
            } else if (tmp.size() == 1) {
                groups[x][y] = setId;
            } else {
                dfsSetId(x, y, setId, groups);
            }
            field[x][y] = Cell.PASSAGE;
            if (startEnd.size() < 2) {
                if (x == 1) {
                    startEnd.add(new Coordinate(0, y));
                } else if (x == height - 2) {
                    startEnd.add(new Coordinate(x + 1, y));
                } else if (y == 1) {
                    startEnd.add(new Coordinate(x, 0));
                } else if (y == width - 2) {
                    startEnd.add(new Coordinate(x, y + 1));
                }
            }
        }

        maze.setStart(startEnd.get(0));
        maze.setEnd(startEnd.get(1));

        return maze;
    }

    private static void dfsSetId(int x, int y, int id, int[][] groups) {
        groups[x][y] = id;
        for (int i = x - 1; i <= x + 1; i++) {
            if (i == x) {
                for (int j = y - 1; j <= y + 1; j += 2) {
                    if (groups[i][j] != 0 && groups[i][j] != id) {
                        dfsSetId(i, j, id, groups);
                    }
                }
            } else if (groups[i][y] != 0 && groups[i][y] != id) {
                dfsSetId(i, y, id, groups);
            }
        }
    }

    @SuppressWarnings("MagicNumber")
    public static Maze mazeBacktracking(int height, int width) {
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

    private static boolean inBox(int x, int y, int height, int width) {
        return (x > 0) && (x < height - 1) && (y > 0) && y < (width - 1);
    }

    private static LinkedList<Coordinate> getValidNeighbours(Coordinate coordinate, Maze maze) {
        int x = coordinate.x();
        int y = coordinate.y();

        var result = new LinkedList<Coordinate>();
        if (!maze.invalidCoordinate(new Coordinate(x - 1, y))) {
            result.add(new Coordinate(x - 1, y));
        }
        if (!maze.invalidCoordinate(new Coordinate(x + 1, y))) {
            result.add(new Coordinate(x + 1, y));
        }
        if (!maze.invalidCoordinate(new Coordinate(x, y - 1))) {
            result.add(new Coordinate(x, y - 1));
        }
        if (!maze.invalidCoordinate(new Coordinate(x, y + 1))) {
            result.add(new Coordinate(x, y + 1));
        }
        return result;
    }
}
