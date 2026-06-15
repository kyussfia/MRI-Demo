package com.mediso.data.defaultvalues;

public enum NumericValue {
    AMPLITUDE("Amplitude", 7, 0.0, 1000.0),
    FREQUENCY("Frequency", 128.332321, 10.0, 1e6),
    PHASE("Phase", 0, -Math.PI / 2.0, Math.PI / 2.0),
    OFFSET("Offset", 0.2, -1.0, 1.0),
    DURATION("Duration", 0.1, 0.0, 60*60*10.),
    DELAY("Delay", 0.0, 0.0, 60*60*10.),
    TIME("Time", 0.0, 0.0, 60*60*10.),
    VOLTAGE("Voltage", 0.0, -1000, 1000),
    CURRENT("Current", 0.0, -10, 10);

    private final String name;
    private final double defaultValue;
    private final double minValue;
    private final double maxValue;

    NumericValue(String name, double defaultValue, double minValue, double maxValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getName() {
        return name;
    }

    public double getDefaultValue() {
        return defaultValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }
}
