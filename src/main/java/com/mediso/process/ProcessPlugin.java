package com.mediso.process;

import com.mediso.data.DataSet;

public interface ProcessPlugin {
    public DataSet process(DataSet dataSet);
    public String getName();
}
