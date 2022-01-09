package src.ttt;

public class TicTacToe {
    public static void main(String[] args) {
        Thread EDT = new Thread(new GUI());
        EDT.start();
    }
}