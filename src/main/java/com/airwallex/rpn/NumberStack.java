package com.airwallex.rpn;
import java.text.NumberFormat;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class NumberStack {

    private final Stack<Double> stack = new Stack<>();

    public void push(Double number) {
        stack.push(number);
    }

    public Double pop() {
        return stack.pop();
    }

    public void clear() {
        stack.clear();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public void copyFrom(NumberStack other) {
        stack.clear();
        stack.addAll(other.stack);
    }

    @Override
    public String toString() {
        return StreamSupport.stream(stack.spliterator(), false)
                .map(NumberStack::formatNumber).collect(Collectors.joining(" "));
    }

    private static String formatNumber(Double number) {
        NumberFormat f = NumberFormat.getInstance();
        f.setMaximumFractionDigits(10);
        f.setMinimumFractionDigits(0);
        f.setGroupingUsed(false);
        return f.format(number);
    }
}
