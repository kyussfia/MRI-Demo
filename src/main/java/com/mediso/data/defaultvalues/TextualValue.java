package com.mediso.data.defaultvalues;

public enum TextualValue {
    MANUFACTURER("Manufacturer", "Mediso", false, 50),
    MODEL("Model", "NanoScan", false, 50),
    SERIAL_NUMBER("Serial number", "221.100.21-asd-1234", false, 50),
    SOFTWARE_VERSION("Software version", "3.11.0", false, 50);

    private String name;
    private String defaultValue;
    private boolean canBeEmpty;
    private int maxLength;

    TextualValue(String name, String defaultValue, boolean canBeEmpty, int maxLength) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.canBeEmpty = canBeEmpty;
        this.maxLength = maxLength;
    }

    public String getName() {
        return name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public boolean isCanBeEmpty() {
        return canBeEmpty;
    }

    public int getMaxLength() {
        return maxLength;
    }
}
