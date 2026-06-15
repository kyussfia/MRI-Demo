package com.mediso.sequence;

import java.util.List;

import com.mediso.data.Header;
import com.mediso.data.defaultvalues.NumericListValue;
import com.mediso.data.param.TextParam;

public class GradientEcho extends Sequence {

    public GradientEcho(Header header) {
        super("Gradient Echo", header);
        header.addParam(new TextParam("Sequence", this.getName()));
        header.getNumericParam("Frequency").setValue(123.46);

        List<Double> coilCalibValues = header.getNumericListParam(NumericListValue.COIL_CALIBRATION_VALUES).getValue();
        for (Double value : coilCalibValues) {
            addEvent(0.321, null, null, null, new SequenceItem(2, 3, 4, 5, 6, 7), new SequenceItem(2, 4, 8, 8, 8, 8), null);
            addEvent(0.5, null, null, null, new SequenceItem(8), new SequenceItem(8), null);
            addEvent(0.321, null, null, null, new SequenceItem(7, 6, 5, 4, 3, 2), new SequenceItem(8, 8, 8, 8, 4, 2), null);
            addEvent(0.33, null, null, null, null, null, new SequenceItem(1, 1, 31, 8, 25, 1));
            addEvent(0.2 + value, new SequenceItem(30, 26, 30, 26, 30, 26), null, null, null, null, null);
            addEvent(1.87, null, new SequenceItem(21, 20, 20, 20, 20, 15), new SequenceItem(23, 27, 26, 30, 28, 22), null, null, null);
        }
    }
}
