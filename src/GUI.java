import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {
    // attributes
    private JFrame frame;
    private JLabel label;
    private JLabel label2;
    private JPanel panel1;
    private JPanel panel2;
    private JButton[][] buttons;
    private JButton reset;
    private int curPlayer;
    private int winner;
    private int numOfPlays;

    //constructor
    public GUI() {
        frame = new JFrame();
        panel1 = new JPanel();
        panel2 = new JPanel();
        label = new JLabel("Play!");
        label2 = new JLabel("Player X turn.");
        buttons = new JButton[3][3];
        curPlayer = 0;
        winner = -1;
        numOfPlays = 0;
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe");
        frame.setSize(500, 500);
        frame.pack();
        frame.setVisible(true);

        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel1.setSize(490, 490);
        panel1.setLayout(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton(" ");
                buttons[row][col].addActionListener(this);
                panel1.add(buttons[row][col]);;
            }
        }

        panel2.setLayout(new GridLayout(0, 1));

        reset = new JButton("Reset");
        reset.addActionListener(this);
        
        panel2.add(panel1);
        panel2.add(reset);
        panel2.add(label);
        panel2.add(label2);

        frame.add(panel2);
    }

    //event handler
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(reset)) {
            Logic.reset();
            label.setText("Play!");
            if (winner == 1) {
                label2.setText("Player O turn.");
                curPlayer = 1;
            }
            else {
                label2.setText("Player X turn.");
                curPlayer = 0;
            }
            winner = -1;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    buttons[row][col].setText(" ");
                }
            }
        }
        else {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (e.getSource().equals(buttons[row][col])) {
                        if (Logic.isValid(row, col)) {
                            Logic.setMap(row, col, curPlayer);
                            if (curPlayer == 0) buttons[row][col].setText("X");
                            else buttons[row][col].setText("O");
                            winner = Logic.findWinner();
                            if (winner == 0) {
                                label.setText("Player X won! Play again.");
                            }
                            else if (winner == 1) {
                                label.setText("Player O won! Play again.");
                            }
                            numOfPlays++;
                            curPlayer = (curPlayer + 1) % 2;
                            if (winner == -1) {
                                if (curPlayer == 0) {
                                    label2.setText("Player X turn.");
                                }
                                else {
                                    label2.setText("Player O turn.");
                                }
                            }
                            else {
                                label2.setText(" ");
                            }
                        }    
                    }
                }
            }
        }
    }
}