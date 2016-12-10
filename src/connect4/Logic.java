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
public class Logic {
    private int cellsLeft = 0;
    private int max;
    private int xsize;
    private int ysize;
    Matrix mainGrid;

    public Logic(Matrix tempGrid) {
        max = 4;//connect 4 or n
        mainGrid = tempGrid;
        cellsLeft = mainGrid.getLeftCells();
        xsize = mainGrid.getXsize();
        ysize = mainGrid.getYsize();
    }

    public boolean set_and_check(int x, int y, int player) {//sets the found coordinate to current player
        mainGrid.setMatrix(x, y, player);
        cellsLeft--;
        return check_one(x, y, 0, 1, player) || check_one(x, y, -1, 1, player) || check_one(x, y, -1, 0, player) || check_one(x, y, 1, 1, player);
    }

    public boolean draw_game() {//checks for draw game
        return cellsLeft == 0;
    }

    private boolean check_one(int x, int y, int dx, int dy, int player) {
        int count = 0;
        int tempx = x;
        int tempy = y;

        while (count < max && valid(tempx, tempy)) {
            if (!mainGrid.matrixEquals(tempx, tempy, player)) {
                break;

            }
            tempx += dx;
            tempy += dy;
            count++;
        }
        tempx = x - dx;
        tempy = y - dy;
        while (count < max && valid(tempx, tempy)) {
            if (!mainGrid.matrixEquals(tempx, tempy, player)) {
                break;
            }
            tempx -= dx;
            tempy -= dy;
            count++;
        }
        return count == max;
    }

    private boolean valid(int x, int y) {
        //if the bounds are set to be >0 only then first row and collumn 
        //doesnt work
        return x >= 0 && x < xsize && y >= 0 && y < ysize;
    }    
}
