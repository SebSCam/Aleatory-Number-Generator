package com.uptc;

import com.uptc.controllers.Controller;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        // Cuadrados Medios
        // System.out.println(new MiddleSquare(727, 4, 5).getAleatory());
        // System.out.println(new Interval().getInterval(8, 10, new MiddleSquare(727, 4,
        // 5).getAleatory()));

        // Congruencial Multiplicativo
        // System.out.println(new Multiplicative(1, 3, 10, 10).getAleatory());
        // System.out.println(new Multiplicative(1, 3, 10, 10).getInterval(8, 12));

        // Congruencial Lineal
        // System.out.println(new Linear(1, 4, 3, 7, 10).getAleatory());
        // System.out.println(new Linear(1, 4, 3, 7, 10).getInterval(8, 10));

        // Uniform
        // System.out.println(new Uniform(10).getAleatory());
        // System.out.println(new Uniform(10).getInterval(8, 10));

        // Normal
        // List<Double> ale = new Uniform(10000).getAleatory();
        // Interval inte = new Interval(15, 17, ale);
        // System.out.println(new Normal(10000, inte, ale).getAleatory());

        new Controller();
    }
}
