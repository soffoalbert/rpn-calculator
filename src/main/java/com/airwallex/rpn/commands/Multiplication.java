package com.airwallex.rpn.commands;

class Multiplication extends DoubleDigitAbstractCommand {

    @Override
    protected double compute(double left, double right) {
        return left * right;
    }
}
