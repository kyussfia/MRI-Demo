package com.mediso.hardware;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Instrument {
    private static final String INSTRUMENT_FILE = "instrument.txt";
    private String model;
    private double delayMultiplier;
    private double baselineAmplitude;
    private double NoiseAmplitude;
    private double gaussianAmplitude;
    private double gaussianSigma;

    public Instrument() {
        // Load instrument settings from file
        try (BufferedReader reader = new BufferedReader(new FileReader(INSTRUMENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    switch (key) {
                        case "model":
                            model = value;
                            break;
                        case "delayMultiplier":
                            delayMultiplier = Double.parseDouble(value);
                            break;
                        case "baselineAmplitude":
                            baselineAmplitude = Double.parseDouble(value);
                            break;
                        case "NoiseAmplitude":
                            NoiseAmplitude = Double.parseDouble(value);
                            break;
                        case "gaussianAmplitude":
                            gaussianAmplitude = Double.parseDouble(value);
                            break;
                        case "gaussianSigma":
                            gaussianSigma = Double.parseDouble(value);
                            break;
                        default:
                            // Handle unrecognized key or invalid format
                            break;
                    }
                }
            }
        } catch (IOException e) {
            // Handle file reading error
        }
    }

    @SuppressWarnings("unused")
    public String getModel() {
        return model;
    }

    public double getDelayMultiplier() {
        return delayMultiplier;
    }

    public double getBaselineAmplitude() {
        return baselineAmplitude;
    }

    public double getNoiseAmplitude() {
        return NoiseAmplitude;
    }

    public double getGaussianAmplitude() {
        return gaussianAmplitude;
    }

    public double getGaussianSigma() {
        return gaussianSigma;
    }

    @SuppressWarnings("unused")
    public void setDelayMultiplier(double delayMultiplier) {
        this.delayMultiplier = delayMultiplier;
    }

    @SuppressWarnings("unused")
    public void setBaselineAmplitude(double baselineAmplitude) {
        this.baselineAmplitude = baselineAmplitude;
    }

    @SuppressWarnings("unused")
    public void setNoiseAmplitude(double NoiseAmplitude) {
        this.NoiseAmplitude = NoiseAmplitude;
    }

    @SuppressWarnings("unused")
    public void setGaussianAmplitude(double gaussianAmplitude) {
        this.gaussianAmplitude = gaussianAmplitude;
    }

    @SuppressWarnings("unused")
    public void setGaussianSigma(double gaussianSigma) {
        this.gaussianSigma = gaussianSigma;
    }

    public void save() {
        // Save instrument settings to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INSTRUMENT_FILE))) {
            writer.write("model=" + model);
            writer.newLine();
            writer.write("baselineAmplitude=" + baselineAmplitude);
            writer.newLine();
            writer.write("NoiseAmplitude=" + NoiseAmplitude);
            writer.newLine();
            writer.write("gaussianAmplitude=" + gaussianAmplitude);
            writer.newLine();
            writer.write("gaussianSigma=" + gaussianSigma);
            writer.newLine();
            writer.write("delayMultiplier=" + delayMultiplier);
            writer.newLine();
        } catch (IOException e) {
            // Handle file writing error
        }
    }
}
