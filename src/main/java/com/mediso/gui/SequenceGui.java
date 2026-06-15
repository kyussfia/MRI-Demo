package com.mediso.gui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.mediso.sequence.Sequence;
import com.mediso.sequence.SequenceItem;

public class SequenceGui {

    private SequenceGui() {
    }

    public static void show(Sequence sequence) {
        JFrame frame = new JFrame(sequence.getName());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int numberOfColumns = sequence.getDelays().size() + 1;
        frame.setSize(69 * numberOfColumns, 300);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        // no space between grid elements
        java.awt.GridLayout layout = new java.awt.GridLayout(7, numberOfColumns);
        layout.setHgap(0);
        layout.setVgap(0);
        panel.setLayout(layout);
        panel.add(createLabel("Delays"));
        for (Double delay : sequence.getDelays()) {
            double roundedDelay = Math.round(delay * 100000.0) / 100000.0;
            panel.add(createLabel(Double.toString(roundedDelay)));
        }
        String[] channelNames = { "TX", "RX", "ACQ", "GRX", "GRY", "GRZ" };
        @SuppressWarnings("unchecked")
        List<SequenceItem>[] channels = new List[] { sequence.getTxChannel(), sequence.getRxChannel(),
                sequence.getAcqChannel(), sequence.getGradXChannel(), sequence.getGradYChannel(), sequence.getGradZChannel() };
        for (int i = 0; i < channelNames.length; i++) {
            panel.add(createLabel(channelNames[i]));
            for (int j = 0; j < channels[i].size(); j++) {
                SequenceItem item = channels[i].get(j);
                panel.add(createLabel(item == null ? "▁▁▁▁▁▁" : item.toString()));
            }
        }
        frame.add(panel);
        frame.setVisible(true);
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        label.setBorder(new EmptyBorder(0, 0, 0, 0));
        return label;
    }
}
