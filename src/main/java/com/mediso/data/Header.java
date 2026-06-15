package com.mediso.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mediso.data.defaultvalues.NumericListValue;
import com.mediso.data.defaultvalues.NumericValue;
import com.mediso.data.defaultvalues.TextualValue;
import com.mediso.data.param.NumericListParam;
import com.mediso.data.param.NumericParam;
import com.mediso.data.param.Param;
import com.mediso.data.param.TextParam;

public class Header {
    private static final String ERROR_MESSAGE = "No such parameter: ";
    protected List<Param<?>> values = new ArrayList<>();

    public Header() {
        Arrays.stream(TextualValue.values())
            .forEach(value -> values.add(new TextParam(value)));
        Arrays.stream(NumericValue.values())
            .forEach(value -> values.add(new NumericParam(value)));
        Arrays.stream(NumericListValue.values())
            .forEach(value -> values.add(new NumericListParam(value)));
    }

    public List<Param<?>> getParams() {
        return values;
    }

    public void addParam(Param<?> param) {
        values.add(param);
    }

    public TextParam getTextParam(TextualValue value) {
        return (TextParam) values.stream()
            .filter(param -> param.getName().equals(value.getName()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE + value.getName()));
    }

    public TextParam getTextParam(String name) {
        return (TextParam) values.stream()
            .filter(param -> param.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE + name));
    }

    public NumericParam getNumericParam(NumericValue value) {
        return (NumericParam) values.stream()
            .filter(param -> param.getName().equals(value.getName()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE + value.getName()));
    }

    public NumericParam getNumericParam(String name) {
        return (NumericParam) values.stream()
            .filter(param -> param.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE + name));
    }

    public NumericListParam getNumericListParam(NumericListValue value) {
        return (NumericListParam) values.stream()
            .filter(param -> param.getName().equals(value.getName()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE + value.getName()));
    }

    public NumericListParam getNumericListParam(String name) {
        return (NumericListParam) values.stream()
            .filter(param -> param.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE + name));
    }

}
