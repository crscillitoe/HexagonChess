package Display;

import Engine.Board.Board;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Board theGame = new Board();
        DrawBoard contentPane = new DrawBoard(theGame);

        JFrame frame = new JFrame("Hexagon Chess");
        frame.setSize(1000 , 1000);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300 , 300));
        contentPane.init();
    }
}
