/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

/**
 *
 * @author Priyansh Rastogi
 */
public class Matrix {
    private int xsize;
    private int ysize;
    private int[][] matrix;
    private int cellsLeft = 0;

    public Matrix() {
        xsize = 7;
        ysize = 6;

        matrix = new int[xsize][ysize];
        for (int i = 0; i < xsize; i++) {
            for (int j = 0; j < ysize; j++) {
                matrix[i][j] = 0;
                cellsLeft++;
            }
        }
    }
    //methods to gain access to internal private data

    public int getLeftCells() {
        return cellsLeft;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public boolean matrixEquals(int a, int b, int c) {
        return matrix [a][b] == c;
    }

    public void setMatrix(int a, int b, int temp_player) {
        matrix[a][b] = temp_player;
    }

    public int getXsize() {//returns the xsize
        return xsize;
    }

    public int getYsize() {//returns the xsize
        return ysize;
    }

    public int findY(int x) {//checks for room in collumn and returns free spot.
        int y = -1;
        for (int i = 0; i < ysize; i++) {
            if (matrix[x][i] == 0) {
                y = i;
            }
        }
        return y;
    }

    public int changeplayer(int player, int max_players) {
        player++;
        if (player > max_players) {
            return 1;
        }
        return player;
    }
}
