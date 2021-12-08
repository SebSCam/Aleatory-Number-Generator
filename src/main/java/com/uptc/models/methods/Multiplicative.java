package com.uptc.models.methods;

import java.util.ArrayList;
import java.util.List;

public class Multiplicative {

    private int x;
    private int a;
    private int m;
    private int quantity;
    private List<Double> aleatory;
    private List<Integer> seeds;

    public Multiplicative(int x, int t, int g, int quantity) {
        this.x = x;
        this.a = (8 * t) + 3;
        this.m = (int) Math.pow(2, g);
        this.aleatory = new ArrayList<>();
        this.quantity = quantity;
        this.seeds = new ArrayList<>();
    }

    public List<Double> getAleatory() {
        generateRandom(calculateSeed(this.x));
        return aleatory;
    }

    private void generateRandom(int seed) {
        if (aleatory.size() < quantity) {
            aleatory.add(calculateNumber(seed));
            int newSeed = calculateSeed(seed);
            seeds.add(seed);
            generateRandom(newSeed);
        }
    }

    private Double calculateNumber(int seed) {
        return (double) seed / (m - 1);
    }

    private int calculateSeed(int xi) {
        return (xi * a) % m;
    }

    public List<Integer> getSeeds() {
        return seeds;
    }
}
