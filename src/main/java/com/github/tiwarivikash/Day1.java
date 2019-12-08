package com.github.tiwarivikash;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1 {

    //    Problem 1
    private static long calculateFuelRequirement(long mass) {
        return Math.floorDiv(mass, 3) - 2;
    }

    //    Problem 2
    private static long calculateTotalFuelRequirement(long mass, long result) {
        long fuelRequirement = calculateFuelRequirement(mass);
        if (fuelRequirement <= 0) {
            return result;
        } else {
            return calculateTotalFuelRequirement(fuelRequirement, result + fuelRequirement);
        }
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(
                Paths.get(Day1.class.getClassLoader().getResource("module.txt").toURI()));
        long answer1 = lines.stream()
                .map(Long::valueOf)
                .map(Day1::calculateFuelRequirement)
                .reduce(0L, Long::sum);
        long answer2 = lines.stream()
                .map(Long::valueOf)
                .map(n -> calculateTotalFuelRequirement(n, 0))
                .reduce(0L, Long::sum);
        System.out.println("Answer 1: " + answer1);
        System.out.println("Answer 2: " + answer2);
    }
}
