package com.uptc.controllers;

import com.uptc.models.methods.Linear;
import com.uptc.models.methods.MiddleSquare;
import com.uptc.models.methods.Multiplicative;
import com.uptc.models.methods.Normal;
import com.uptc.models.methods.Uniform;
import com.uptc.views.Command;
import com.uptc.views.Header;
import com.uptc.views.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.uptc.utils.Interval;
import com.uptc.utils.Ulitities;

public class Controller implements ActionListener {

    private Window window;

    public Controller() {
        window = new Window(this);
        window.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Header header;
        switch (Command.valueOf(e.getActionCommand())) {
            case GENERATE_MIDD:
                header = window.getMidSrtHeader();
                manageMiddleSqr(header);
                break;
            case GENERATE_LINEAS:
                header = window.getLinearHeader();
                manageLinear(header);
                break;
            case GENERATE_MULT:
                header = window.getMultHeader();
                manageMultiplicative(header);
                break;
            case GENERATE_NORMAL:
                header = window.getNormalHeader();
                manageNormal(header);
                break;
            case GENERATE_UNIFORM:
                header = window.getUniformHeader();
                manageUniform(header);
                break;
        }
    }

    // Uniform
    private void manageUniform(Header header) {
        Uniform uniform = new Uniform(header.getQuantity());
        List<Double> aleatory = uniform.getAleatory();
        List<Double> interval = new Interval(header.getMin(), header.getMax(), aleatory).getInterval();

        manageUniformTable(aleatory, interval);
    }

    private void manageUniformTable(List<Double> aleatory, List<Double> interval) {
        window.resetUniformTable();
        window.resetUniformGraphic();
        window.setUniformGraphicData(interval);
        List<Object[]> elements = Ulitities.toObjectArrayList(aleatory, interval);
        for (Object[] element : elements) {
            window.setUniformTableRow(element);
        }
    }

    // Normal
    private void manageNormal(Header header) {
        Normal normal = new Normal(0, null, null);
        List<Double> aleatory = normal.getAleatory();
        List<Double> interval = new Interval(header.getMin(), header.getMax(), aleatory).getInterval();

        manageNormalTable(aleatory, interval);
    }

    private void manageNormalTable(List<Double> aleatory, List<Double> interval) {
        window.resetMultiplicativeTable();
        window.resetMultiplicativeGraphic();
        window.setMultiplicativeGraphicData(interval);
        List<Object[]> elements = Ulitities.toObjectArrayList(aleatory, interval);
        for (Object[] element : elements) {
            window.setMultiplicativeTableRow(element);
        }
    }

    // Multiplicative
    private void manageMultiplicative(Header header) {
        Multiplicative multiplicative = new Multiplicative(header.getXi(), header.getT(), header.getG(),
                header.getQuantity());
        List<Double> aleatory = multiplicative.getAleatory();
        System.out.println(aleatory);
        List<Double> interval = new Interval(header.getMin(), header.getMax(), aleatory).getInterval();

        manageMultiplicativeTable(multiplicative.getSeeds(), aleatory, interval);
    }

    private void manageMultiplicativeTable(List<Integer> seeds, List<Double> aleatory, List<Double> interval) {
        window.resetMultiplicativeTable();
        window.resetMultiplicativeGraphic();
        window.setMultiplicativeGraphicData(interval);
        List<Object[]> elements = Ulitities.toObjectArrayList(seeds, aleatory, interval);
        for (Object[] element : elements) {
            window.setMultiplicativeTableRow(element);
        }
    }

    // Linear
    private void manageLinear(Header header) {
        Linear linear = new Linear(header.getXi(), header.getK(), header.getC(), header.getG(), header.getQuantity());
        List<Double> aleatory = linear.getAleatory();
        List<Double> interval = new Interval(header.getMin(), header.getMax(), aleatory).getInterval();

        manageLinearTable(linear.getSeeds(), aleatory, interval);
    }

    private void manageLinearTable(List<Integer> seeds, List<Double> aleatory, List<Double> interval) {
        window.resetLinearTable();
        window.resetLinearGraphic();
        window.setLinearGraphicData(interval);
        List<Object[]> elements = Ulitities.toObjectArrayList(seeds, aleatory, interval);
        for (Object[] element : elements) {
            window.setLinearTableRow(element);
        }
    }

    // Middle
    private void manageMiddleSqr(Header header) {
        MiddleSquare middleSquare = new MiddleSquare(header.getSeed(), header.getDigits(), header.getQuantity());
        List<Double> aleatory = middleSquare.getAleatory();
        List<Double> interval = new Interval(header.getMin(), header.getMax(), aleatory).getInterval();

        System.out.println(interval.size());
        manageMiddleTable(middleSquare.getCenters(), aleatory, interval);
    }

    private void manageMiddleTable(List<Integer> xi, List<Double> ri, List<Double> ni) {
        window.resetMiddleTable();
        window.resetMiddleGraphic();
        window.setGraphicData(ni);
        List<Object[]> elements = Ulitities.toObjectArrayList(xi, ri, ni);
        for (Object[] element : elements) {
            window.setMiddTableRow(element);
        }
    }
}
