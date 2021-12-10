package com.uptc.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileManager {

    public static void writeToArchive(List<Double> numbers, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (Double number : numbers) {
            writer.write(String.valueOf(number));
            writer.write("#");
        }
        writer.close();
    }
}
