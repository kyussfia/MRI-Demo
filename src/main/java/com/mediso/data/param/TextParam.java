package com.mediso.data.param;

import java.io.PrintStream;

import com.mediso.data.defaultvalues.TextualValue;

public class TextParam implements Param<String> {
    private final String name;
    private String text;
    private boolean canBeEmpty;

    public TextParam(TextualValue value) {
        this.name = value.getName();
        this.text = value.getDefaultValue();
        this.canBeEmpty = value.isCanBeEmpty();
    }

    public TextParam(String name, String text) {
        this.name = name;
        this.text = text;
        this.canBeEmpty = false;
    }

    public TextParam(String name, String text, boolean canBeEmpty) {
        this.name = name;
        this.text = text;
        this.canBeEmpty = canBeEmpty;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return text;
    }

    @Override
    public String getValueAsString() {
        return text;
    }

    @Override
    public void setValue(String value) {
        if (value != null && !value.isEmpty()) {
            this.text = value;
        }
    }

    @Override
    public void setValueFromString(String value) {
        setValue(value);
    }

    @Override
    public void print(PrintStream out) {
        out.println(text);
    }

    @SuppressWarnings("unused")
    public boolean isCanBeEmpty() {
        return canBeEmpty;
    }

    @SuppressWarnings("unused")
    public void setCanBeEmpty(boolean canBeEmpty) {
        this.canBeEmpty = canBeEmpty;
    }
}
