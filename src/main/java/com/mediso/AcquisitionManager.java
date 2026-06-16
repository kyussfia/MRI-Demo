package com.mediso;

import java.io.IOException;

import com.mediso.data.DataSet;
import com.mediso.data.defaultvalues.NumericValue;
import com.mediso.gui.DataGui;
import com.mediso.hardware.HardwareDriver;
import com.mediso.process.AverageAndSlidingWindowAndRescale;
import com.mediso.process.BaselineCorrection;
import com.mediso.process.ProcessPlugin;
import com.mediso.sequence.Sequence;

public class AcquisitionManager {
    private AcquisitionManager() {}

    @SuppressWarnings("java:S2142")
    public static void startAcquisition(Sequence sequence) {
        double maxAmplitude = sequence.getHeader()
            .getNumericParam(NumericValue.AMPLITUDE)
            .getValue();
        DataGui gui = new DataGui(maxAmplitude);
        ProcessPlugin[] pluginList = new ProcessPlugin[] {
            new BaselineCorrection(),
            new AverageAndSlidingWindowAndRescale()
        };
        gui.run();
        try(HardwareDriver hardwareDriver = new HardwareDriver("localhost", 8080)) {
            double[] data = hardwareDriver.runAcquisition(sequence, gui);
            DataSet dataSet = new DataSet(sequence.getHeader(), data);
            for (ProcessPlugin plugin : pluginList) {
                dataSet = plugin.process(dataSet);
                gui.update(dataSet.getData(), plugin.getName() + " done");
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException error) {
            gui.showMessage("An error occured: " + error.getMessage());
            //noinspection CallToPrintStackTrace
            error.printStackTrace();
        }
    }
}
