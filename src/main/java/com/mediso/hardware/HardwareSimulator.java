package com.mediso.hardware;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Logger;

import com.mediso.sequence.SequenceEvent;

public class HardwareSimulator extends Thread {
    private final Random random = new Random();
    private final Instrument instrument = new Instrument();
    private final ServerSocket serverSocket;

    public HardwareSimulator(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void close() {
        try {
            instrument.save();
            Logger.getGlobal().info("Instrument saved");
            this.serverSocket.close();
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings({"java:S2142", "InfiniteLoopStatement"})
    public void run() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                SequenceEvent input;
                double baselineAmplitude = instrument.getBaselineAmplitude();
                double sigma = instrument.getGaussianSigma();
                double gaussianAmplitude = instrument.getGaussianAmplitude();
                while ((input = (SequenceEvent) in.readObject()) != null) {
                    Thread.sleep((long) (input.getDelay() * instrument.getDelayMultiplier()));
                    for (int i = 0; i < 128; i++) {
                        double gaussian = gaussianAmplitude * Math.exp(-0.5 * Math.pow(i - 64.0, 2) / (sigma * sigma));
                        double noise = random.nextGaussian() * instrument.getNoiseAmplitude();
                        out.writeDouble(gaussian + noise + baselineAmplitude);
                    }
                    out.flush();
                }
                in.close();
                out.close();
            }
        } catch (InterruptedException | IOException | ClassNotFoundException exception) {
            // Do nothing
        }
    }
}
