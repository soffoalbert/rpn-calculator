package com.airwallex.rpn.commands;

abstract class DoubleDigitAbstractCommand extends AbstractDigitCommand {

    @Override
    protected final boolean isBinary() {
        return true;
    }
}
