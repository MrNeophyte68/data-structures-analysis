package Maze;

import java.util.*;
import java.util.stream.Collectors;

public class Maze {
    /** TODO
     * Returns the distance of the shortest path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the shortest path within the maze, null if not solvable
     */
    public static Integer findShortestPath(ArrayList<ArrayList<Tile>> maze) {
        if (maze == null || maze.isEmpty()) return null;
        int rows = maze.size();
        int colums = maze.get(0).size();

        //first part, let the maze be a 2d matrix, we use two for loops to iterate over each tile to find the entrance
        //the readme says that the entrance and the exit are interchangeable thus it doesn't matter which exit we find first
        //as we are only looking for the shortest distance between them, when one of the exits is found we break using a label to break out of
        //the outer for loop
        //==================================================================================================================
        //time complexity, O(n * m) for part one, n: rows, m: columns
        int startX = -1, startY = -1;
        outerloop:
        for(int r = 0; r < rows; r++) {
            for (int c = 0; c < colums; c++) {
                if (maze.get(r).get(c) == Tile.Exit) {
                    startX = r;
                    startY = c;
                    break outerloop;
                }
            }
        }
        if (startX == -1) return null; //if there is no exit in the maze we return null

        //second part, we are trying to find the shortest path using a BFS algorithm by exploring each path layer by layer, thus
        // we create a copy of the maze and store the places that were visited, we also need a movement list (left, right, down, up)
        //which will act like edges in a graph, and we need a queue to avoid looping redundancy when exploring the tiles, the queue will also
        //store the current distance of its progress which is why we store an array of size 3 inside the queue, note: we store the arrays at the end of the
        //queue
        //==================================================================================================================
        //
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, 0});
        boolean[][] visited = new boolean[rows][colums];
        visited[startX][startY] = true;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        //third part, as we previously stated, the queue stored a row position, a column position and its current distance
        //thus we pop the head of the queue to get the stored row (r), column (c) positions and its current distance
        //==================================================================================================================
        //
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int distance = current[2];

            //if we find another exit which does not have the same position as start of x and y we return the distance
            if (maze.get(r).get(c) == Tile.Exit && (r != startX || c != startY)) {
                return distance;
            }

            //from the current positon of r and c we explore layer by layer by going left, right, down, up which we will call new_r and new_c
            for (int[] d : directions) {
                int new_r = r + d[0];
                int new_c = c + d[1];
                //to avoid going out of bounds we can only add to queue or say that we visited the tile if row positon is between
                //0 and maxRow (rows) and column is between 0 and maxColumns (colums), the tile must also not be a wall and has to not
                //have been visited, only then can we add it to queue and visited, and then we repeat the third part until
                //we have checked left, right, down, up from its current position
                if (new_r >= 0 && new_r < rows && new_c >= 0 && new_c < colums && maze.get(new_r).get(new_c) != Tile.Wall && !visited[new_r][new_c]) {
                    visited[new_r][new_c] = true;
                    queue.add(new int[]{new_r, new_c, distance+1});
                }
            }
        }
        return null; // we return null if we were unsuccessful at finding a distance between two exits
    }

    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }
}

