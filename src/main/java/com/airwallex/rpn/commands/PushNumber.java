package com.airwallex.rpn.commands;

import com.airwallex.rpn.Undoable;

class PushNumber extends NumberStackCommand implements Undoable {

    @Override
    public void execute() {
        numberStack.push(new Double(operatorValue));

        undoHistory.push(this);
    }

    @Override
    public void undo() {
        numberStack.pop();
    }
}
