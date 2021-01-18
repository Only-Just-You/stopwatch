package org.example.watch;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestStopWatch {
    public static void main(String[] args) {
        Frame f = new Frame("秒表");
        f.add(new StopWatch());
        f.setSize(250,100);
        f.setLocation(350,350);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
