package com.airwallex.rpn.commands;

class Division extends DoubleDigitAbstractCommand {

    @Override
    protected double compute(double left, double right) {
        return left / right;
    }
}
