package com.mediso.data;

public class DataSet extends Header {
    private final double[] data;

    public DataSet(Header header, double[] data) {
        super.values = header.values;
        this.data = data;
    }

    public double[] getData() {
        return data;
    }
}
