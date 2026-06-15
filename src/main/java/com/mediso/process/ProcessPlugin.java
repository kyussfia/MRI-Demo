package com.mediso.process;

import com.mediso.data.DataSet;

public interface ProcessPlugin {
    DataSet process(DataSet dataSet);
    String getName();
}
