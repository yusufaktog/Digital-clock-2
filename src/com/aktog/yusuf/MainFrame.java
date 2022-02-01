package com.aktog.yusuf;

import javax.swing.*;


public class MainFrame extends JFrame {
    DigitalClock clockPanel;
    public MainFrame() {
        clockPanel = new DigitalClock();
        this.add(clockPanel);
        clockPanel.startClock();

        loadPreferences();
    }

    final void loadPreferences() {
        this.setTitle("Digital Clock");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater((() -> new MainFrame().setVisible(true)));
    }
}