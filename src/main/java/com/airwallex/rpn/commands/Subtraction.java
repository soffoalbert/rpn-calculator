package com.airwallex.rpn.commands;


class Subtraction extends DoubleDigitAbstractCommand {

    @Override
    protected double compute(double left, double right) {
        return left - right;
    }
}
