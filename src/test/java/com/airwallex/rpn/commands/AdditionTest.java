package com.airwallex.rpn.commands;

import com.airwallex.rpn.InsufficientParameterException;
import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdditionTest extends NumberStackCommandTest<Addition> {

    public AdditionTest() {
        super(Addition::new);
    }

    @Test
    public void testCompute() {

        double result = command.compute(5.0, 4.2);

        assertEquals(9.2, result, 0.0);
    }

    @Test
    public void testExecute() {
        when(numbers.size()).thenReturn(2);
        when(numbers.pop()).thenReturn(5.0, 2.0);
        command.execute();
        verify(numbers).push(7.0);
        verify(undoHistory).push(command);
    }

    @Test
    public void shouldThrowExceptionIfNotEnoughNumber() {
        command.setOperatorValue("+");
        command.setOperatorPosition(15);

        expectedEx.expect(InsufficientParameterException.class);
        expectedEx.expectMessage("operator + (position: 15): insufficient parameters");

        when(numbers.size()).thenReturn(1);
        command.execute();
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        numbers.push(11.0);
        numbers.push(9.0);

        command.execute();
        assertEquals("20", numbers.toString());

        command.undo();
        assertEquals("11 9", numbers.toString());
    }
}
