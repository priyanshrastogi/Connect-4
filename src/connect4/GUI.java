/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Priyansh Rastogi
 */
public class GUI {
    
    private JFrame frame;
    private JLabel[][] slots;
    private JButton[] buttons;
    //variables used in grid
    private int xsize = 7;
    private int ysize = 6;
    private int currentPlayer = 1;
    private String player1name;
    private String player2name;
    //game variables to communicate with top program
    private boolean hasWon = false;
    private boolean hasDraw = false;
    private boolean quit = false;
    private boolean newGame = false;
    //making of grid and Logic
    Matrix mainGrid = new Matrix();
    Logic mainLogic = new Logic(mainGrid); //create game Logic

    public GUI() {

        frame = new JFrame("Connect 4");
  
        player1name = JOptionPane.showInputDialog(frame, "Enter Player 1's name","Connect 4: Player Information",JOptionPane.INFORMATION_MESSAGE);
        if(player1name==null)
            player1name = "Player 1";
        player2name = JOptionPane.showInputDialog(frame, "Enter Players 2's name","Connect 4: Player Information",JOptionPane.INFORMATION_MESSAGE);
        if(player2name==null)
            player2name = "Player 2";

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(new GridLayout(xsize, ysize + 1));

        slots = new JLabel[xsize][ysize];
        buttons = new JButton[xsize];

        for (int i = 0; i < xsize; i++) {
            buttons[i] = new JButton("" + (i + 1));
            buttons[i].setActionCommand("" + i);
            buttons[i].addActionListener(
                    new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            int a = Integer.parseInt(e.getActionCommand());
                            int y = mainGrid.findY(a);  //checking for space in collumn
                            if (y != -1) {
                                //sets a place to current player
                                if (mainLogic.set_and_check(a, y, currentPlayer)) {
                                    hasWon = true;
                                } else if (mainLogic.draw_game()) {//checks for drawgame
                                    hasDraw = true;
                                } else {
                                    //change player
                                    currentPlayer = mainGrid.changeplayer(currentPlayer, 2);
                                if(currentPlayer==1) {
                                    frame.setTitle("Connect 4: "  + player1name + "'s turn");
                                   }
                                else {
                                    frame.setTitle("Connect 4: "  + player2name + "'s turn");
                                }    
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "choose another one", "column is filled", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    });
            panel.add(buttons[i]);
        }
        for (int column = 0; column < ysize; column++) {
            for (int row = 0; row < xsize; row++) {
                slots[row][column] = new JLabel();
                slots[row][column].setHorizontalAlignment(SwingConstants.CENTER);
                slots[row][column].setBorder(new LineBorder(Color.black));
                panel.add(slots[row][column]);
            }
        }

        //jframe stuff
        frame.setContentPane(panel);
        frame.setSize(
                700, 600);
        frame.setVisible(
                true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateBoard() {//keeps the gui in sync with the logic and matrix
        for (int row = 0; row < xsize; row++) {
            for (int column = 0; column < ysize; column++) {
                if (mainGrid.matrixEquals(row, column, 1)) {
                    slots[row][column].setOpaque(true);
                    slots[row][column].setBackground(Color.green);
                }
                if (mainGrid.matrixEquals(row, column, 2)) {
                    slots[row][column].setOpaque(true);
                    slots[row][column].setBackground(Color.red);
                }
            }
        }
    }

    public void showWon() {
        String winner;
        if(currentPlayer==1) {
            winner = player1name + " wins";
        }
        else {
            winner = player2name + " wins";
        }
        int n = JOptionPane.showConfirmDialog(frame,"new game?",winner,JOptionPane.YES_NO_OPTION);
        if (n < 1) {
            frame.dispose();
            newGame = true;
        } else {
            frame.dispose();
            quit = true;
        }
    }

    public void showDraw() {
        String winner = "draw game";
        int n = JOptionPane.showConfirmDialog(frame,"new game?",winner,JOptionPane.YES_NO_OPTION);
        if (n < 1) {
            frame.dispose();
            newGame = true;
        } else {
            frame.dispose();
            quit = true;
        }
    }

    public boolean getHasWon() {
        return hasWon;
    }

    public boolean getHasDraw() {
        return hasDraw;
    }

    public boolean getQuit() {
        return quit;
    }

    public boolean getNewGame() {
        return newGame;
    }

    public static void main(String[] args) {
        GUI UI = new GUI();
    }    
}
