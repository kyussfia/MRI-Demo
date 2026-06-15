package com.mediso.data.param;

import java.io.PrintStream;

import com.mediso.data.defaultvalues.NumericValue;

public class NumericParam extends AbstractParam<Double> {
    private Double value;
    private double minValue;
    private double maxValue;

    public NumericParam(NumericValue value) {
        super.name = value.getName();
        this.value = value.getDefaultValue();
        this.minValue = value.getMinValue();
        this.maxValue = value.getMaxValue();
    }

    public NumericParam(String name, Double number) {
        super.name = name;
        this.value = number;
        this.minValue = Integer.MIN_VALUE;
        this.maxValue = Integer.MAX_VALUE;
    }

    public NumericParam(String name, Double number, int minValue, int maxValue) {
        super.name = name;
        this.value = number;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String getValueAsString() {
        return value.toString();
    }

    @Override
    public void setValue(Double value) {
        if (value < this.minValue) {
            value = this.minValue;
        } else if (value > this.maxValue) {
            value = this.maxValue;
        }
        this.value = value;
    }

    @Override
    public void setValueFromString(String value) {
        setValue(Double.parseDouble(value));
    }

    @Override
    public void print(PrintStream out) {
        out.println(value);
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }
}
