package com.airwallex.rpn.commands;

class Undo extends NumberStackCommand {

    @Override
    public void execute() {
        if (!undoHistory.isEmpty()) {
            undoHistory.pop().undo();
        }
    }
}
