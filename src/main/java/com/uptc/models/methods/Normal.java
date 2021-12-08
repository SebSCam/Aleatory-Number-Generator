package com.uptc.models.methods;

import java.util.ArrayList;
import java.util.List;
import com.uptc.utils.Interval;

public class Normal {

    private double average;
    private double deviation;
    private List<Double> aleatory;
    private List<Double> seeds;

    public Normal(int quantity, Interval inte, List<Double> aleatoryXi) {
        aleatory = new ArrayList<>();
        this.average = inte.getAverage();
        this.deviation = inte.getDeviation();
        this.seeds = aleatoryXi;
        generateRandom();
    }

    public List<Double> getAleatory() {
        generateRandom();
        return aleatory;
    }

    private void generateRandom() {
        for (Double x : seeds) {
            double num = Math.exp(-Math.pow(x - average, 2) / (2 * Math.pow(deviation, 2)))
                    / (Math.sqrt(2 * Math.PI) * deviation);
            aleatory.add(num);
        }
    }
}
