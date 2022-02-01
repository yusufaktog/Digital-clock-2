package com.aktog.yusuf;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class DigitalClock extends JPanel {
    final static int PANEL_WIDTH = 1200;
    final static int PANEL_HEIGHT = 800;
    Thread secondsThread;
    int seconds;
    int minutes;
    int hours;
    static final int delay = 1000;

    public DigitalClock() {
        loadPreferences();
    }

    final void loadPreferences() {

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setLayout(null);
    }


    public void startClock() {
        LocalDateTime time = LocalDateTime.now();
        seconds = time.getSecond();
        minutes = time.getMinute();
        hours = time.getHour();
        secondsThread = new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seconds++;

                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }

                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                }

                if (hours == 24) {
                    hours = 0;
                    minutes = 0;
                    seconds = 0;
                }
                //System.out.println("H: " + hours + ", M: " + minutes + ", S: " + seconds);
            }
        });
        secondsThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int fontSize = 150;
        int middlePoint = PANEL_HEIGHT / 2 - fontSize;

        g.setColor(Color.yellow);
        g.setFont(new Font("Ink Free", Font.BOLD, fontSize));


        // first colon
        g.fillOval(middlePoint + 170, 325, 25, 25);
        g.fillOval(middlePoint + 170, 375, 25, 25);
        // second colon

        g.fillOval(middlePoint + 470, 325, 25, 25);
        g.fillOval(middlePoint + 470, 375, 25, 25);

        g.drawString(adjustDigitStringLength(String.valueOf(hours)), middlePoint - 40, 400);
        g.drawString(adjustDigitStringLength(String.valueOf(minutes)), middlePoint + 240, 400);
        g.drawString(adjustDigitStringLength(String.valueOf(seconds)), middlePoint + 540, 400);
        repaint();
    }

    // adjust precision
    public String adjustDigitStringLength(String digit) {
        return digit.length() == 1 ? "0" + digit : digit;
    }

    public void stopClock() {
        this.secondsThread.interrupt();
    }

}