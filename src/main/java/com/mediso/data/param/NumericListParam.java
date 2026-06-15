package com.mediso.data.param;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.mediso.data.defaultvalues.NumericListValue;

public class NumericListParam extends AbstractParam<List<Double>> {
    private List<Double> numbers;
    private double minValue;
    private double maxValue;
    private int minLength;
    private int maxLength;

    public NumericListParam(NumericListValue value) {
        super.name = value.getName();
        this.numbers = value.getDefaultValue();
        this.minValue = value.getMinValue();
        this.maxValue = value.getMaxValue();
        this.minLength = value.getMinLength();
        this.maxLength = value.getMaxLength();
    }

    public NumericListParam(String name, List<Double> numbers) {
        super.name = name;
        this.numbers = numbers;
        this.minValue = Double.NEGATIVE_INFINITY;
        this.maxValue = Double.POSITIVE_INFINITY;
        this.minLength = 0;
        this.maxLength = Integer.MAX_VALUE;
    }

    public NumericListParam(String name, List<Double> numbers, double minValue, double maxValue) {
        super.name = name;
        this.numbers = numbers;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.minLength = 0;
        this.maxLength = Integer.MAX_VALUE;
    }

    public NumericListParam(String name, List<Double> numbers, double minValue, double maxValue, int minLength, int maxLength) {
        super.name = name;
        this.numbers = numbers;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public List<Double> getValue() {
        return numbers;
    }

    @Override
    public String getValueAsString() {
        return numbers.stream().map(d -> d.toString()).collect(Collectors.joining(","));
    }

    @Override
    public void setValue(List<Double> value) {
        if (value.size() < minLength || value.size() > maxLength) {
            return;
        }
        for (int i = 0; i < value.size(); i++) {
            if (value.get(i) < minValue) {
                value.set(i, minValue);
            } else if (value.get(i) > maxValue) {
                value.set(i, maxValue);
            }
        }
        this.numbers = value;
    }

    @Override
    public void setValueFromString(String value) {
        setValue(Arrays.stream(value.split(","))
            .map(Double::parseDouble)
            .collect(Collectors.toList()));
    }

    @Override
    public void print(PrintStream out) {
        out.println("[" + numbers.stream().map(Object::toString).collect(Collectors.joining(", ")) + "]");
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

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
