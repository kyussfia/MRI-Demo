package com.mediso.gui;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.mediso.AcquisitionManager;
import com.mediso.data.Header;
import com.mediso.data.param.Param;
import com.mediso.sequence.Sequence;

public class AcquisitionManagerGui {
    private AcquisitionManagerGui() {}

    public static void createWindow(Sequence sequence) {
        JFrame frame = new JFrame("Acquisition Manager");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Header header = sequence.getHeader();
        for (Param<?> param : header.getParams()) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel label = new JLabel(param.getName());
            JTextField textField = new JTextField(param.getValueAsString(), 20);
            JButton saveButton = new JButton("Save");

            saveButton.addActionListener(e -> {
                param.setValueFromString(textField.getText());
                textField.setText(param.getValueAsString());
            });

            row.add(label);
            row.add(textField);
            row.add(saveButton);

            panel.add(row);
        }
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton showSequence = new JButton("Show sequence");
        showSequence.addActionListener(e -> SequenceGui.show(sequence));
        row.add(showSequence);
        JButton startAcquisition = new JButton("Start acquisition");
        startAcquisition.addActionListener(e -> {
            frame.setEnabled(false);
            AcquisitionManager.startAcquisition(sequence);
            frame.setEnabled(true);
        });

        row.add(startAcquisition);
        panel.add(row);

        JScrollPane scrollPane = new JScrollPane(panel);

        // Add the scroll pane to the frame
        frame.add(scrollPane);
        frame.pack();
        panel.setPreferredSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}
