package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Generate {
    private static final int[] NEIGHBOR = {-1, 1};

    public static Maze mazeKruskal(int height, int width) {
        var maze = new Maze(height, width, null, null);
        var field = maze.getMaze();
        int groupId = 1;
        var groups = new int[height][width];
        var randomCoordinates = new ArrayList<Coordinate>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = Cell.WALL;
                randomCoordinates.add(new Coordinate(i, j));
            }
        }

        Collections.shuffle(randomCoordinates);

        for (var coordinate : randomCoordinates) {
            int x = coordinate.x();
            int y = coordinate.y();
            if (inBox(x, y, height, width)) {
                HashSet<Integer> tmp = new HashSet<>();
                boolean cont = false;
                int setId = 0;

                for (var i : NEIGHBOR) {
                    for (var j : NEIGHBOR) {
                        var neighborGroup = groups[x + i][y + j];
                        if (neighborGroup == 0 || cont) {
                            continue;
                        } else if (tmp.contains(neighborGroup)) {
                            cont = true;
                        } else {
                            tmp.add(neighborGroup);
                            setId = neighborGroup;
                        }
                    }
                }

                if (cont) {
                    continue;
                }

                if (setId == 0) {
                    groups[x][y] = groupId;
                    groupId++;
                } else {
                    dfsSetId(x, y, setId, groups);
                }

                field[x][y] = Cell.PASSAGE;
            }
        }

        return maze;
    }

    private static void dfsSetId(int x, int y, int id, int[][] groups) {
        groups[x][y] = id;
        for (var i : NEIGHBOR) {
            for (var j : NEIGHBOR) {
                if (groups[x + i][y + j] != 0 && groups[x + i][y + j] != id) {
                    dfsSetId(x + i, y + i, id, groups);
                }
            }
        }
    }

    private static boolean inBox(int x, int y, int height, int width) {
        return (x > 0) && (x < height - 1) && (y > 0) && y < (width - 1);
    }
}
