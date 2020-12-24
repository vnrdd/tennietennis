package org.rud.tennis.manage;

import javax.swing.*;

public class MainFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("TENNIE TENNIS");
        frame.setContentPane(new GamePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
