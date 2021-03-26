package com.airwallex.rpn;

import com.airwallex.rpn.commands.Operator;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private static final Pattern LINE_PATTERN = Pattern.compile("\\S+");
    private final NumberStack numberStack = new NumberStack();
    private final UndoHistory undoHistory = new UndoHistory();
    private final String EXIT = "exit";

    public void run() {
        System.out.println("Please enter number or operator separated by space, type 'exit' to quit.");

        try (Scanner scanner = new Scanner(System.in)) {
            String line;
            while (true) {
                line = scanner.nextLine();
                if (EXIT.equalsIgnoreCase(line)) {
                    break;
                }

                try {
                    processLine(line);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                } finally {
                    System.out.println("stack: " + numberStack);
                }
            }
        }
    }

    public void processLine(String line) {
        Matcher matcher = LINE_PATTERN.matcher(line);
        while (matcher.find()) {
            processValue(matcher.group(), matcher.start() + 1);
        }
    }

    private void processValue(String value, int position) {
        Operator op = Operator.findByValue(value).orElse(null);
        Objects.requireNonNull(op, "invalid number or unknown operator: '" + value + "'");
        op.createCommand(numberStack, undoHistory, value, position).execute();
    }

    public String getStackMessage() {
        return numberStack.toString();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }
}
