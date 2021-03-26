package com.airwallex.rpn.commands;

class SquareRoot extends SingleDigitAbstractCommand {

    @Override
    protected double compute(double operand) {
        return Math.sqrt(operand);
    }
}
