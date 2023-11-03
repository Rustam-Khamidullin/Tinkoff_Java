package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Generate {

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
                PriorityQueue<Integer> tmp = new PriorityQueue<>();
                tmp.add()

                if (id.size() == 1 && id.contains(0)) {
                    field[x][y] = Cell.PASSAGE;
                    groups[x][y] = groupId;
                    groupId++;
                } else {

                }
            }
        }

        return maze;
    }

    private static boolean inBox(int x, int y, int height, int width) {
        return (x > 0) && (x < height - 1) && (y > 0) && y < (width - 1);
    }
}
