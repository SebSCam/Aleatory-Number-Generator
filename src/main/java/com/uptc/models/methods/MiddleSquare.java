package com.uptc.models.methods;

import java.util.ArrayList;
import java.util.List;

public class MiddleSquare {

    private int seed;
    private int digits;
    private int quantity;
    private List<Double> aleatory;
    private List<Integer> centers;

    public MiddleSquare(int seed, int digits, int quantity) {
        this.seed = seed;
        this.digits = digits;
        this.quantity = quantity;
        this.aleatory = new ArrayList<Double>();
        this.centers = new ArrayList<>();
    }

    public List<Double> getAleatory() {
        generateRandom(this.seed);
        return aleatory;
    }

    private void generateRandom(int seed) {
        if (aleatory.size() < quantity) {
            int pow = (int) Math.pow(seed, 2);
            int center = calculateCenter(pow);
            centers.add(seed);
            aleatory.add(center / Math.pow(10, digits));
            generateRandom(center);
        }
    }

    private Integer calculateCenter(int pow) {
        String aux = String.valueOf(pow);
        int step = (digits * 2) - aux.length();
        char[] number = new char[digits * 2];
        number = fillNumber(step, aux, number);
        return Integer.valueOf(getCenterNumber(aux, number));
    }

    private String getCenterNumber(String aux, char[] number) {
        String newSeed = "";
        int position = calculatePosition(number);
        for (int i = position; i < position + digits; i++) {
            newSeed += number[i];
        }
        return newSeed;
    }

    private int calculatePosition(char[] number) {
        int rest = number.length - digits;
        return Math.floorDiv(rest, 2);
    }

    private char[] fillNumber(int step, String aux, char[] number) {
        for (int i = 0; i < step; i++) {
            number[i] = '0';
        }
        int index = 0;
        for (int i = step; i < number.length; i++) {
            number[i] = aux.charAt(index++);
        }
        return number;
    }

    public List<Integer> getCenters() {
        return centers;
    }
}