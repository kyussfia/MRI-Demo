package com.mediso.data.defaultvalues;

import java.util.List;

public enum NumericListValue {
    RECEIVER_GAIN_LIST("Receiver Gain List", List.of(0.2, 1.0), 0, 10, 1, 10),
    TRANSMITTER_GAIN_LIST("Transmitter Gain List", List.of(0., 1.0, -2.0, 3.0, -4.0), -10, 10, 0, 10),
    COIL_CALIBRATION_VALUES("Coil Calibration Values", List.of(0.0), -10, 10, 1, 20),;
    
    private final String name;
    private final List<Double> defaultValues;
    private final double minValue;
    private final double maxValue;
    private final int minLength;
    private final int maxLength;

    NumericListValue(String name, List<Double> defaultValues, double minValue, double maxValue, int minLength, int maxLength) {
        this.name = name;
        this.defaultValues = defaultValues;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public String getName() {
        return name;
    }

    public List<Double> getDefaultValue() {
        return defaultValues;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }
}
