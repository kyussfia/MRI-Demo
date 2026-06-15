package com.mediso.data.param;

import java.io.PrintStream;

public interface Param<T> {
    String getName();
    T getValue();
    String getValueAsString();
    void setValue(T value);
    void setValueFromString(String value);
    @SuppressWarnings("unused")
    void print(PrintStream out);
}
