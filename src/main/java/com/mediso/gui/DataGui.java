package com.mediso.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class DataGui extends JPanel implements Runnable {
    private double[] data;
    private String message;
    private final double maxAmplitude;

    public DataGui(double maxAmplitude) {
        this.setPreferredSize(new Dimension(640, 480));
        this.maxAmplitude = maxAmplitude;
    }

    public void update(double[] dataSet, String message) {
        SwingUtilities.invokeLater(() -> {
            this.data = dataSet;
            this.message = message;
            paintComponent(getGraphics());
        });
    }

    public void showMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            this.message = message;
            paintComponent(getGraphics());
        });
    }

    @Override
    public void run() {
        JFrame f = new JFrame("Data viewer");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (message != null) {  
            AffineTransform at = g2d.getTransform();
            g2d.setTransform(at);
            g2d.setColor(Color.blue);
            g2d.drawString(message, 10, 10);
        }
        int w = getWidth();
        int h = getHeight();
        g2d.setColor(Color.gray);
        g2d.drawLine(0, h / 2, w, h / 2);
        if (this.data == null) {
            return;
        }
        g2d.translate(0, h / 2);
        g2d.scale(1, -1);
        g2d.setColor(Color.blue);
        int x1 = 0;
        int y1 = valueToPixel(data[0], h);
        int x2, y2;
        for (int i = 1; i < data.length; i++) {
            x2 = positionToPixel(i);
            y2 = valueToPixel(data[i], h);
            g2d.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }
    }

    private int positionToPixel(int i) {
        return (int) Math.round(((double) i) / data.length * getWidth());
    }

    private int valueToPixel(double value, int height) {
        return (int) Math.round(value / maxAmplitude / 2 * height);
    }
}
