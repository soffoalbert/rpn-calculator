package com.airwallex.rpn.commands;

import com.airwallex.rpn.Command;
import com.airwallex.rpn.NumberStack;
import com.airwallex.rpn.UndoHistory;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public enum Operator {

    ADD("+", Addition::new),
    SUBTRACT("-", Subtraction::new),
    MULTIPLY("*", Multiplication::new),
    DIVIDE("/", Division::new),
    SQRT("sqrt", SquareRoot::new),
    UNDO("undo", Undo::new),
    CLEAR("clear", Clear::new),
    PUSH_NUMBER(null, PushNumber::new);

    private String value;
    private Supplier<? extends NumberStackCommand> supplier;

    Operator(String value, Supplier<? extends NumberStackCommand> supplier) {
        this.value = value;
        this.supplier = supplier;
    }

    public Command createCommand(NumberStack numberStack, UndoHistory undoHistory, String operatorValue, int operatorPosition) {
        NumberStackCommand command = supplier.get();
        command.setNumberStack(numberStack);
        command.setUndoHistory(undoHistory);
        command.setOperatorValue(operatorValue);
        command.setOperatorPosition(operatorPosition);

        return command;
    }

    public static Optional<Operator> findByValue(String value) {
        Objects.requireNonNull(value);

        return Arrays.stream(Operator.values()).filter(op ->
                value.equalsIgnoreCase(op.value) || op == PUSH_NUMBER && isValidNumberInput(value)
        ).findFirst();
    }

    private static boolean isValidNumberInput(String value) {
        try {
            new Double(value);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
