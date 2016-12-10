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
public class Connect4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            int state = 0;
            GUI UI = new GUI();
            while (state != -1) {  
                switch (state) {
                    case 0:     //runtime
                        UI.updateBoard();
                        if (UI.getHasWon()) {
                            state = 1;
                        } else if (UI.getHasDraw()) {
                            state = 2;
                        } else if (UI.getNewGame()) {
                            UI = new GUI();
                            state = 0;
                        }
                        break;
                    case 1:     //endgame with winner
                        UI.showWon();
                        if (UI.getQuit()) {
                            state = -1;
                        } else  if (UI.getNewGame()) {
                            UI = new GUI();
                            state = 0;
                        }
                        break;
                    case 2:     //endgame with draw
                        UI.showDraw();
                        if (UI.getQuit()) {
                            state = -1;
                        } else if (UI.getNewGame()) {
                            UI = new GUI();
                            state = 0;
                        }
                        break;
                }
            }
        }
}
    
    
