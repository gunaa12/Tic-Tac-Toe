package src.ttt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener, Runnable {
    // attributes
    private JFrame frame;
    private JLabel label1;
    private JLabel label2;
    private JPanel panel1;
    private JPanel panel2;
    private JButton[][] buttons;
    private JButton reset;
    private int curPlayer;
    private int winner;

    //implementing the runnable method
    public void run() {
        Logic.reset();
        setUpFramework();
        curPlayer = 0;
        winner = -1;
    }

    //constructor
    private void setUpFramework() {
        // setting up the frame
        frame = new JFrame();
        frame.setTitle("Tic Tac Toe");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // setting up the labels
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        label1 = new JLabel("Play!");
        label2 = new JLabel("Player X turn.");
        panel1.add(label1);
        panel1.add(label2);
        frame.add(panel1, BorderLayout.NORTH);

        //setting up the buttons
        panel2 = new JPanel();
        buttons = new JButton[3][3];

        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel2.setLayout(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton(" ");
                buttons[row][col].addActionListener(this);
                panel2.add(buttons[row][col]);;
            }
        }
        frame.add(panel2, BorderLayout.CENTER);

        reset = new JButton("Reset");
        reset.setMaximumSize(new Dimension(500, 50));
        reset.addActionListener(this);
        frame.add(reset, BorderLayout.SOUTH);
    }

    // update buttons
    private void updateButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int num = Logic.getMap()[row][col];
                if (num == 0) {
                    buttons[row][col].setText("X");
                }
                else if (num == 1) {
                    buttons[row][col].setText("O");
                }
                else {
                    buttons[row][col].setText(" ");
                }
            }
        }
    }

    //event handler
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(reset)) {
            Logic.reset();
            label1.setText("Play!");
            if (winner == 1) {
                label2.setText("Player O turn.");
                curPlayer = 1;
            }
            else if (winner == 0) {
                label2.setText("Player X turn.");
                curPlayer = 0;
            }
            winner = -1;
            updateButtons();
        }
        else {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (e.getSource().equals(buttons[row][col])) {
                        if ((Logic.isValid(row, col)) && (winner == -1)) {
                            //updating buttons
                            Logic.setMap(row, col, curPlayer);
                            updateButtons();

                            //updating labels
                            winner = Logic.findWinner();
                            label2.setText("");
                            if (winner == 0) {
                                label1.setText("Player X won! Play again.");
                            }
                            else if (winner == 1) {
                                label1.setText("Player O won! Play again.");
                            }
                            else {
                                curPlayer = (curPlayer + 1) % 2;
                                if (curPlayer == 0) {
                                    label2.setText("Player X turn.");
                                }
                                else {
                                    label2.setText("Player O turn.");
                                }
                            }
                        }    
                    }
                }
            }
        }
    }
}