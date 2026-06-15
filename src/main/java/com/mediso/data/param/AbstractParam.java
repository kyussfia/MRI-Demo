package com.mediso.data.param;

public abstract class AbstractParam<T> implements Param<T> {
    protected String name;

    @Override
    public String getName() {
        return name;
    }

}
