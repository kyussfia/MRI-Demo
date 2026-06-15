package com.mediso.sequence;

import java.io.Serializable;
import java.util.Arrays;

public class SequenceItem implements Serializable {
    private final int[] data;

    public SequenceItem(int d1, int d2, int d3, int d4, int d5, int d6) {
        this.data = new int[] { d1, d2, d3, d4, d5, d6 };
    }

    public SequenceItem(int constantValue) {
        this.data = new int[6];
        Arrays.fill(this.data, constantValue);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int d : data) {
            sb.append(Character.toChars(0x2580 + d));
        }
        return sb.toString();
    }

    public int[] getData() {
        return data;
    }
}
