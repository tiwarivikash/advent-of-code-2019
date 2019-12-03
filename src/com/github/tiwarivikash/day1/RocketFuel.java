package com.github.tiwarivikash.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RocketFuel {

    /**
     * Divide by 3, rount it and subtract 2.
     * @param mass
     * @return fuel measurement
     */
    private static final long calculateFuelRequirement(long mass) {
        return (mass / 3) - 2;
    }


    public static void main(String[] args) throws IOException {
        long result = 0;
        List<String> lines = Files.readAllLines(
                Path.of("/Users/vikash/Development/advent-of-code-2019/src/com/github/tiwarivikash/day1/input.txt"));
        for (String s : lines) {
            long value = calculateFuelRequirement(Long.valueOf(s));
            result = result + value;
        }
        System.out.println(result);
    }
}
