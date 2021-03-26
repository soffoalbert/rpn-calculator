package com.airwallex.rpn.commands;

import com.airwallex.rpn.InsufficientParameterException;
import com.airwallex.rpn.Undoable;

abstract class AbstractDigitCommand extends NumberStackCommand implements Undoable {

    private double left;
    private double right;

    @Override
    public final void execute() {
        takeOperands();

        double result = compute(left, right);
        numberStack.push(result);

        undoHistory.push(this);
    }

    @Override
    public final void undo() {
        numberStack.pop();

        numberStack.push(left);
        if (isBinary()) {
            numberStack.push(right);
        }
    }

    private void takeOperands() {
        if (isBinary() && numberStack.size() < 2 ||
                !isBinary() && numberStack.size() < 1) {
            throw new InsufficientParameterException(operatorValue, operatorPosition);
        }

        if (isBinary()) {
            right = numberStack.pop();
        }
        left = numberStack.pop();
    }

    protected abstract boolean isBinary();

    protected abstract double compute(double left, double right);
}
