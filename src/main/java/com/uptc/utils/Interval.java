package com.uptc.utils;

import java.util.ArrayList;
import java.util.List;

public class Interval {

    private ArrayList<Double> interval;
    private double average;
    private double deviation;
    private double variation;
    private List<Double> aleatory;
    private int min;
    private int max;

    public Interval(int min, int max, List<Double> aleatory) {
        this.aleatory = aleatory;
        this.min = min;
        this.max = max;
        calculateAverage();
        calculateVariation();
        calculateDeviation();
    }

    public List<Double> getInterval() {
        this.interval = new ArrayList<>();
        for (Double ri : aleatory) {
            interval.add(min + (max - min) * ri);
        }
        return interval;
    }

    private void calculateAverage() {
        double sum = 0;
        for (Double num : getInterval()) {
            sum += num;
        }
        average = sum / aleatory.size();
    }

    private void calculateVariation() {
        double rango;
        for (Double num : interval) {
            rango = Math.pow(num - average, 2f);
            variation += rango;
        }
    }

    private double calculateDeviation() {
        deviation = Math.sqrt(variation);
        return deviation;
    }

    public double getAverage() {
        return average;
    }

    public double getDeviation() {
        return deviation;
    }

    public double getVariation() {
        return variation;
    }
}