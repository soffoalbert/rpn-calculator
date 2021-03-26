package com.airwallex.rpn.commands;

import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class PushNumberTest extends NumberStackCommandTest<PushNumber> {

    public PushNumberTest() {
        super(PushNumber::new);
    }

    @Test
    public void testExecute() {
        command.setOperatorValue(  "5.1");

        command.execute();

        verify(numbers).push(5.1);
        verify(undoHistory).push(command);
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        command.setOperatorValue("20");
        command.execute();
        assertEquals("20", numbers.toString());

        command.undo();
        assertEquals("", numbers.toString());
    }
}
