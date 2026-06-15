package com.mediso.sequence;

import java.util.ArrayList;
import java.util.List;

import com.mediso.data.Header;

public abstract class Sequence {
    private final String name;
    protected final Header header;
    private final List<Double> delays = new ArrayList<>();
    private final List<SequenceItem> txChannel = new ArrayList<>();
    private final List<SequenceItem> rxChannel = new ArrayList<>();
    private final List<SequenceItem> acqChannel = new ArrayList<>();
    private final List<SequenceItem> gradXChannel = new ArrayList<>();
    private final List<SequenceItem> gradYChannel = new ArrayList<>();
    private final List<SequenceItem> gradZChannel = new ArrayList<>();

    protected Sequence(String name, Header header) {
        this.name = name;
        this.header = header;
    }

    protected void addEvent(double delay, SequenceItem tx, SequenceItem rx, SequenceItem acq, SequenceItem gradX,
            SequenceItem gradY, SequenceItem gradZ) {
        delays.add(delay);
        txChannel.add(tx);
        rxChannel.add(rx);
        acqChannel.add(acq);
        gradXChannel.add(gradX);
        gradYChannel.add(gradY);
        gradZChannel.add(gradZ);
    }

    public Header getHeader() {
        return header;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return delays.size();
    }

    public List<SequenceEvent> getEvents() {
        List<SequenceEvent> events = new ArrayList<>();
        for (int i = 0; i < delays.size(); i++) {
            events.add(new SequenceEvent(delays.get(i), txChannel.get(i), rxChannel.get(i), acqChannel.get(i),
                    gradXChannel.get(i), gradYChannel.get(i), gradZChannel.get(i)));
        }
        return events;
    }

    public List<Double> getDelays() {
        return delays;
    }

    public List<SequenceItem> getTxChannel() {
        return txChannel;
    }

    public List<SequenceItem> getRxChannel() {
        return rxChannel;
    }

    public List<SequenceItem> getAcqChannel() {
        return acqChannel;
    }

    public List<SequenceItem> getGradXChannel() {
        return gradXChannel;
    }

    public List<SequenceItem> getGradYChannel() {
        return gradYChannel;
    }

    public List<SequenceItem> getGradZChannel() {
        return gradZChannel;
    }
}
