package com.airwallex.rpn.commands;

import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class ClearTest extends NumberStackCommandTest<Clear> {

    public ClearTest() {
        super(Clear::new);
    }

    @Test
    public void testExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);
        numbers.push(2.0);
        numbers.push(1.0);

        command.execute();

        assertTrue("number stack should be empty after cleared", numbers.isEmpty());
        verify(undoHistory).push(command);
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        numbers.push(3.0);
        numbers.push(4.0);

        command.execute();
        assertEquals("", numbers.toString());

        command.undo();
        assertEquals("3 4", numbers.toString());
    }

    @Test
    public void shouldBehaveProperlyOnEmptyStack() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        command.execute();
        assertEquals("", numbers.toString());

        command.undo();
        assertEquals("", numbers.toString());
    }
}
