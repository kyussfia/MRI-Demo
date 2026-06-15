package com.mediso.process;

import com.mediso.data.DataSet;

public class BaselineCorrection implements ProcessPlugin {
    @Override
    public String getName() {
        return "baseline correction";
    }

    @Override
    public DataSet process(DataSet dataSet) {
        double[] data = dataSet.getData();
        final double baselineAmplitude = -1.5;
        for (int i = 0; i < data.length; i++) {
            data[i] -= baselineAmplitude;
        }
        return dataSet;
    }

}
