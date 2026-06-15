package com.mediso.hardware;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Logger;

import com.mediso.gui.DataGui;
import com.mediso.sequence.Sequence;
import com.mediso.sequence.SequenceEvent;

public class HardwareDriver {
    private final Socket clientSocket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public HardwareDriver(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public double[] runAcquisition(Sequence sequence, DataGui gui) throws IOException {
        double[] result = new double[sequence.getLength() * 128];
        int offset = 0;
        int counter = 0;
        for (SequenceEvent event : sequence.getEvents()) {
            out.writeObject(event);
            out.flush();
            for (int i = 0; i < 128; i++) {
                result[i + offset] = in.readDouble();
            }
            gui.update(Arrays.copyOfRange(result, offset, offset + 128), "Data row #" + counter);
            offset += 128;
            counter++;
        }
        Logger.getGlobal().info("Acquisition done!");
        out.writeObject(null);
        return result;
    }

    @SuppressWarnings("unused")
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
