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
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import com.uptc.utils.FileManager;
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
                try {
                    manageMiddleSqr(header);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "App Failure!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case GENERATE_LINEAS:
                header = window.getLinearHeader();
                try {
                    manageLinear(header);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "App Failure!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case GENERATE_MULT:
                header = window.getMultHeader();
                try {
                    manageMultiplicative(header);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "App Failure!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case GENERATE_NORMAL:
                header = window.getNormalHeader();
                try {
                    manageNormal(header);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "App Failure!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case GENERATE_UNIFORM:
                header = window.getUniformHeader();
                try {
                    manageUniform(header);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "App Failure!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    // Uniform
    private void manageUniform(Header header) throws IOException {
        Uniform uniform = new Uniform(header.getQuantity());
        List<Double> aleatory = uniform.getAleatory();
        List<Double> interval = new Interval(header.getMin(), header.getMax(), aleatory).getInterval();
        FileManager.writeToArchive(aleatory, "files/uniform.num");
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
    private void manageNormal(Header header) throws IOException {
        List<Double> aleatory = new Uniform(header.getXi()).getAleatory();
        List<Double> seeds = new Uniform(header.getQuantity()).getAleatory();
        Normal normal = new Normal(header.getQuantity(), new Interval(header.getMin(), header.getMax(), aleatory),
                seeds);
        FileManager.writeToArchive(normal.getAleatory(), "files/normal.num");
        manageNormalTable(seeds, normal.getAleatory());
    }

    private void manageNormalTable(List<Double> seeds, List<Double> normList) {
        window.resetNormalTable();
        window.resetNormalGraphic();
        window.setNormalGraphicData(normList);
        List<Object[]> elements = Ulitities.toObjectArrayList(seeds, normList);
        for (Object[] element : elements) {
            window.setNormalTableRow(element);
        }
    }

    // Multiplicative
    private void manageMultiplicative(Header header) throws IOException {
        Multiplicative multiplicative = new Multiplicative(header.getXi(), header.getT(), header.getG(),
                header.getQuantity());
        List<Double> aleatory = multiplicative.getAleatory();
        System.out.println(aleatory);
        List<Double> interval = new Interval(header.getMin(), header.getMax(), aleatory).getInterval();

        FileManager.writeToArchive(aleatory, "files/multiplicative.num");
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
    private void manageLinear(Header header) throws IOException {
        Linear linear = new Linear(header.getXi(), header.getK(), header.getC(), header.getG(), header.getQuantity());
        List<Double> aleatory = linear.getAleatory();
        List<Double> interval = new Interval(header.getMin(), header.getMax(), aleatory).getInterval();

        FileManager.writeToArchive(aleatory, "files/linear.num");
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
    private void manageMiddleSqr(Header header) throws IOException {
        MiddleSquare middleSquare = new MiddleSquare(header.getSeed(), header.getDigits(), header.getQuantity());
        List<Double> aleatory = middleSquare.getAleatory();
        List<Double> interval = new Interval(header.getMin(), header.getMax(), aleatory).getInterval();

        FileManager.writeToArchive(aleatory, "files/middle-sqr.num");
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
