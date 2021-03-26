package com.airwallex.rpn.commands;

abstract class SingleDigitAbstractCommand extends AbstractDigitCommand {

    @Override
    protected final boolean isBinary() {
        return false;
    }

    @Override
    protected final double compute(double left, double right) {
        return compute(left);
    }

    protected abstract double compute(double operand);
}
