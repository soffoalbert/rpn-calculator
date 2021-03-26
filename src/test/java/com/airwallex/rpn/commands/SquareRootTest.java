package com.airwallex.rpn.commands;

import com.airwallex.rpn.InsufficientParameterException;
import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SquareRootTest extends NumberStackCommandTest<SquareRoot> {

    public SquareRootTest() {
        super(SquareRoot::new);
    }

    @Test
    public void testCompute() {
        double result = command.compute(9.0);

        assertEquals(3.0, result, 0.0);
    }

    @Test
    public void testExecute() {
        when(numbers.size()).thenReturn(1);
        when(numbers.pop()).thenReturn(9.0);
        command.execute();
        verify(numbers).push(3.0);
        verify(undoHistory).push(command);
    }

    @Test
    public void shouldThrowExceptionIfNotEnoughNumber() {
        command.setOperatorValue("sqrt");
        command.setOperatorPosition(15);

        expectedEx.expect(InsufficientParameterException.class);
        expectedEx.expectMessage("operator sqrt (position: 15): insufficient parameters");

        when(numbers.size()).thenReturn(0);
        command.execute();
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        numbers.push(16.0);

        command.execute();
        assertEquals("4", numbers.toString());

        command.undo();
        assertEquals("16", numbers.toString());
    }
}
