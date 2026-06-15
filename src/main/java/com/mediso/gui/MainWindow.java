package com.mediso.gui;

import com.mediso.data.Header;
import com.mediso.sequence.GradientEcho;
import com.mediso.sequence.Sequence;
import com.mediso.sequence.SpinEcho;

import javax.swing.*;

public class MainWindow {
    public static JFrame show() {
        JFrame frame = new JFrame("MRI Demo");
        JPanel panel = new JPanel();

        String[] options = { "Gradient Echo", "Spin Echo" };
        JComboBox<String> dropdown = new JComboBox<>(options);
        panel.add(dropdown);

        JButton openSequence = new JButton("Open sequence");
        openSequence.addActionListener(e -> {
            Header header = new Header();
            Sequence sequence;
            if ("Gradient Echo".equals(dropdown.getSelectedItem())) {
                sequence = new GradientEcho(header);
            } else {
                sequence = new SpinEcho(header);
            }
            AcquisitionManagerGui.createWindow(sequence);
        });
        panel.add(openSequence);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }
}
