package com.github.tiwarivikash;

import java.util.Arrays;
import java.util.List;
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

    /**
     * Problem 1.
     */
    private static String processOpcode(String inputStr) {
        List<String> inputStrList = Arrays.asList(inputStr.split(","));
        List<Integer> inputList = inputStrList.stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
        for (int i = 0; i < inputList.size(); i = i + 4) {
            Opcode opcode = Opcode.fromSymbol(inputList.get(i));
            if (opcode == Opcode.HALT) {
                break;
            }
            int arg1 = inputList.get(inputList.get(i + 1));
            int arg2 = inputList.get(inputList.get(i + 2));
            int result = opcode.applyAsInt(arg1, arg2);
            inputList.set(inputList.get(i + 3), result);
        }

        return inputList.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        String input = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,13,19,2,9,19,23,1,23,6,27,1,13,27,31,1,31,10,35,1,9,35,39,1,39,9,43,2,6,43,47,1,47,5,51,2,10,51,55,1,6,55,59,2,13,59,63,2,13,63,67,1,6,67,71,1,71,5,75,2,75,6,79,1,5,79,83,1,83,6,87,2,10,87,91,1,9,91,95,1,6,95,99,1,99,6,103,2,103,9,107,2,107,10,111,1,5,111,115,1,115,6,119,2,6,119,123,1,10,123,127,1,127,5,131,1,131,2,135,1,135,5,0,99,2,0,14,0";
        String result = processOpcode(input);
        System.out.println(result);
    }

}
