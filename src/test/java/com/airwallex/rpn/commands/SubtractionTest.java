package com.airwallex.rpn.commands;

import com.airwallex.rpn.InsufficientParameterException;
import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SubtractionTest extends NumberStackCommandTest<Subtraction> {

    public SubtractionTest() {
        super(Subtraction::new);
    }

    @Test
    public void testCompute() {
        double result = command.compute(4, 3);

        assertEquals(1.0, result, 0.0);
    }

    @Test
    public void testExecute() {
        when(numbers.size()).thenReturn(2);
        when(numbers.pop()).thenReturn(4.0, 1.0);
        command.execute();
        verify(numbers).push(-3.0);
        verify(undoHistory).push(command);
    }

    @Test
    public void shouldThrowExceptionIfNotEnoughNumber() {
        command.setOperatorValue("-");
        command.setOperatorPosition(15);

        expectedEx.expect(InsufficientParameterException.class);
        expectedEx.expectMessage("operator - (position: 15): insufficient parameters");

        when(numbers.size()).thenReturn(1);
        command.execute();
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        numbers.push(9.0);
        numbers.push(8.0);

        command.execute();
        assertEquals("1", numbers.toString());

        command.undo();
        assertEquals("9 8", numbers.toString());
    }
}
