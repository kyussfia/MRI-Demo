package com.mediso;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JFrame;

import com.mediso.gui.MainWindow;
import com.mediso.hardware.HardwareSimulator;

public class Main {
    public static void main(String[] args) throws IOException {
        HardwareSimulator hardwareSimulator = new HardwareSimulator(8080);
        hardwareSimulator.start();
        JFrame mainWindow = MainWindow.show();
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Logger.getGlobal().info("Window is closing");
                hardwareSimulator.close();
                System.exit(0);
            }
        };
        mainWindow.addWindowListener(windowListener);
    }
}