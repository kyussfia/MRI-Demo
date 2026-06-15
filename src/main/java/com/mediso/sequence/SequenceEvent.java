package com.mediso.sequence;

import java.io.Serializable;

public class SequenceEvent implements Serializable {
    private final Double delay;
    private final SequenceItem tx;
    private final SequenceItem rx;
    private final SequenceItem acq;
    private final SequenceItem gradX;
    private final SequenceItem gradY;
    private final SequenceItem gradZ;
    
    public SequenceEvent(Double delay, SequenceItem tx, SequenceItem rx, SequenceItem acq, SequenceItem gradX, SequenceItem gradY, SequenceItem gradZ) {
        this.delay = delay;
        this.tx = tx;
        this.rx = rx;
        this.acq = acq;
        this.gradX = gradX;
        this.gradY = gradY;
        this.gradZ = gradZ;
    }

    public Double getDelay() {
        return delay;
    }

    public SequenceItem getTx() {
        return tx;
    }

    public SequenceItem getRx() {
        return rx;
    }

    public SequenceItem getAcq() {
        return acq;
    }

    public SequenceItem getGradX() {
        return gradX;
    }

    public SequenceItem getGradY() {
        return gradY;
    }

    public SequenceItem getGradZ() {
        return gradZ;
    }
}
