//Гаврилян, ПП-12, 19 варіант
package com.company;
import javax.swing.*;
import java.awt.*;

class ComputerMoves extends TicTacToe implements Runnable {
    public void run() {
        setCountOfMoves();
        Color color2 = new Color(146, 249, 253);
        if (getCountOfMoves() % 2 == 0) {
            countOfMovesComputer++;
            computerMove =(int) (Math.random()*10);
            while(computerMove == 0 || Moves.contains(computerMove)) {
                if (countOfMovesComputer >=5) break;
                computerMove =(int) (Math.random()*10);
            }
            add(computerMove);
            switch (computerMove) {
                case 1: {
                    computer[0][0] = 1;
                    buttonA1.setFont(buttonFont);
                    buttonA1.setText("O");
                    buttonA1.setBackground(color2);
                    buttonA1.setEnabled(false);
                    break;
                }
                case 2: {
                    computer[0][1] = 1;
                    buttonA2.setFont(buttonFont);
                    buttonA2.setText("O");
                    buttonA2.setBackground(color2);
                    buttonA2.setEnabled(false);
                    break;
                }
                case 3: {
                    computer[0][2] = 1;
                    buttonA3.setFont(buttonFont);
                    buttonA3.setText("O");
                    buttonA3.setBackground(color2);
                    buttonA3.setEnabled(false);
                    break;
                }
                case 4: {
                    computer[1][0] = 1;
                    buttonB1.setFont(buttonFont);
                    buttonB1.setText("O");
                    buttonB1.setBackground(color2);
                    buttonB1.setEnabled(false);
                    break;
                }
                case 5: {
                    computer[1][1] = 1;
                    buttonB2.setFont(buttonFont);
                    buttonB2.setText("O");
                    buttonB2.setBackground(color2);
                    buttonB2.setEnabled(false);
                    break;
                }
                case 6: {
                    computer[1][2] = 1;
                    buttonB3.setFont(buttonFont);
                    buttonB3.setText("O");
                    buttonB3.setBackground(color2);
                    buttonB3.setEnabled(false);
                    break;
                }
                case 7:{
                    computer[2][0] = 1;
                    buttonC1.setFont(buttonFont);
                    buttonC1.setText("O");
                    buttonC1.setBackground(color2);
                    buttonC1.setEnabled(false);
                    break;
                }
                case 8: {
                    computer[2][1] = 1;
                    buttonC2.setFont(buttonFont);
                    buttonC2.setText("O");
                    buttonC2.setBackground(color2);
                    buttonC2.setEnabled(false);
                    break;
                }
                case 9: {
                    computer[2][2] = 1;
                    buttonC3.setFont(buttonFont);
                    buttonC3.setText("O");
                    buttonC3.setBackground(color2);
                    buttonC3.setEnabled(false);
                    break;
                }

            }
            if (countOfMovesComputer >= 3) {
                if (statusOfComputer == 0) {
                    statusOfComputer = check(computer);
                }
            }
        }
        if (statusOfComputer == 1) {
            result.setText("You are a loser :(");
            result.setFont(buttonFont1);
            for (JButton b : buttons) {
                b.setEnabled(false);
            }
        } else if (statusOfComputer == 0 && countOfMovesComputer >=5) {
            result.setText("Dead Heat");
            for (JButton b : buttons) {
                b.setEnabled(false);
            }
        }
    }
}