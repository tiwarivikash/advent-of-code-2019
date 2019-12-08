package com.github.tiwarivikash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

enum Opcode implements IntBinaryOperator {
    PLUS(1) {
        @Override
        public int applyAsInt(int left, int right) {
            return left + right;
        }
    },
    MULTIPLICATION(2) {
        @Override
        public int applyAsInt(int left, int right) {
            return left * right;
        }
    },
    HALT(99) {
        @Override
        public int applyAsInt(int left, int right) {
            return -1;
        }
    };

    private final int symbol;

    Opcode(final int symbol) {
        this.symbol = symbol;
    }

    public static Opcode fromSymbol(int val) {
        for (Opcode opcode : Opcode.values()) {
            if (opcode.symbol == val) {
                return opcode;
            }
        }
        throw new IllegalArgumentException("Invalid Opcode");
    }
}

/**
 * Advent of Code - Problem Day 2.
 */
public class Day2 {

    private static List<Integer> buildInputList(String input) {
        List<String> inputStrList = Arrays.asList(input.split(","));
        return inputStrList.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    private static String generateOutput(List<Integer> outputList) {
        return outputList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    /**
     * Problem 1.
     */
    private static List<Integer> processOpcode(List<Integer> inputList) {
        List<Integer> outputList = new ArrayList<>(inputList);
        for (int i = 0; i < outputList.size(); i = i + 4) {
            Opcode opcode = Opcode.fromSymbol(outputList.get(i));
            if (opcode == Opcode.HALT) {
                break;
            }
            int arg1 = outputList.get(outputList.get(i + 1));
            int arg2 = outputList.get(outputList.get(i + 2));
            int result = opcode.applyAsInt(arg1, arg2);
            outputList.set(outputList.get(i + 3), result);
        }
        return outputList;
    }

    /**
     * Problem 2.
     */
    private static Optional<Output> findNounAndVerb(String input, int output) {
        final List<Integer> inputList = buildInputList(input);
        int min = 0, max = 99;
        for (int noun = min; noun <= max; noun++) {
            for (int verb = min; verb <= max; verb++) {
                List<Integer> tempList = new ArrayList<>(inputList);
                tempList.set(1, noun);
                tempList.set(2, verb);
                List<Integer> result = processOpcode(tempList);
                if (result.get(0) == output) {
                    System.out.println("Final Sequence: " + generateOutput(result));
                    return Optional.of(new Output(tempList.get(1), tempList.get(2)));
                }
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        // Problem 1
        String input1 = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,13,19,2,9,19,23,1,23,6,27,1,13,27,31,1,31,10,35,1,9,35,39,1,39,9,43,2,6,43,47,1,47,5,51,2,10,51,55,1,6,55,59,2,13,59,63,2,13,63,67,1,6,67,71,1,71,5,75,2,75,6,79,1,5,79,83,1,83,6,87,2,10,87,91,1,9,91,95,1,6,95,99,1,99,6,103,2,103,9,107,2,107,10,111,1,5,111,115,1,115,6,119,2,6,119,123,1,10,123,127,1,127,5,131,1,131,2,135,1,135,5,0,99,2,0,14,0";
        List<Integer> result = processOpcode(buildInputList(input1));
        System.out.println("Answer1: " + result.get(0));

        //Problem 2
        String input2 = "1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,13,19,2,9,19,23,1,23,6,27,1,13,27,31,1,31,10,35,1,9,35,39,1,39,9,43,2,6,43,47,1,47,5,51,2,10,51,55,1,6,55,59,2,13,59,63,2,13,63,67,1,6,67,71,1,71,5,75,2,75,6,79,1,5,79,83,1,83,6,87,2,10,87,91,1,9,91,95,1,6,95,99,1,99,6,103,2,103,9,107,2,107,10,111,1,5,111,115,1,115,6,119,2,6,119,123,1,10,123,127,1,127,5,131,1,131,2,135,1,135,5,0,99,2,0,14,0";
        Optional<Output> output = findNounAndVerb(input2, 19690720);
        if (!output.isEmpty()) {
            System.out.println("Answer2: " + (100 * output.get().noun + output.get().verb));
        }
    }

}

class Output {
    public final int noun;
    public final int verb;

    Output(int noun, int verb) {
        this.noun = noun;
        this.verb = verb;
    }
}
