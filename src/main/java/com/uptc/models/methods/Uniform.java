package com.uptc.models.methods;

import java.util.ArrayList;
import java.util.List;

public class Uniform {

    private int quantity;
    private List<Double> aleatory;

    public Uniform(int quantity) {
        this.quantity = quantity;
        this.aleatory = new ArrayList<>();
    }

    public List<Double> getAleatory() {
        generateRandom();
        return aleatory;
    }

    private void generateRandom() {
        while (quantity > 0) {
            aleatory.add(Math.random());
            quantity -= 1;
        }
    }
}
