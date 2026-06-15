package com.mediso.process;

import com.mediso.data.DataSet;
import com.mediso.data.defaultvalues.NumericValue;

public class AverageAndSlidingWindowAndRescale implements ProcessPlugin {
    @Override
    public String getName() {
        return "averaging and sliding window smoothing";
    }

    @Override
    public DataSet process(DataSet dataSet) {
        double[] data = dataSet.getData();
        // calculate the average of every 128th data points
        /// resulting in an output of 128 data points
        int Number_of_repeats = data.length / 128;
        double[] r = new double[128];
        for (int i = 0; i < 128; i++) {
        double sumOfValues = 0;
        for (int j = 0; j < Number_of_repeats; j++) {
        sumOfValues += data[j * 128 + i];
        }
        r[i] = sumOfValues / Number_of_repeats;
        }
        // ----------
        // smoothen the data with sliding window averaging
        double[] r2 = new double[r.length];
        for (int i = 0; i < r.length; i++) {
            double sum = 0;
            int s = Math.max(i - 5, 0);
            int e = Math.min(i + 5, r.length);
            for (int j = s; j < e; j++) {      sum += r[j]; }
            r2[i] = sum/(e-s);
        }
        // --------
        // Rescale data
        double m_value = r2[0];
        for (int i = 1; i < r2.length; i++) {
            if (r2[i] > m_value) {
                m_value = r2[i];
            }
        }
        double newMax_value = dataSet.getNumericParam(NumericValue.AMPLITUDE).getValue();
        for (int i = 0; i < r2.length; i++) {
            r2[i] /= m_value / newMax_value;
        }
        return new DataSet(dataSet, r2);
    }
}
