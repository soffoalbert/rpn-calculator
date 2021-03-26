package com.airwallex.rpn.commands;

import com.airwallex.rpn.InsufficientParameterException;
import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MultiplicationTest extends NumberStackCommandTest<Multiplication> {

    public MultiplicationTest() {
        super(Multiplication::new);
    }

    @Test
    public void testCompute() {

        double result = command.compute(2, 3);

        assertEquals(6, result, 0.0);
    }

    @Test
    public void testExecute() {
        when(numbers.size()).thenReturn(2);
        when(numbers.pop()).thenReturn(4.0, 10.0);
        command.execute();
        verify(numbers).push(40.0);
        verify(undoHistory).push(command);
    }

    @Test
    public void shouldThrowExceptionIfNotEnoughNumber() {
        command.setOperatorValue("*");
        command.setOperatorPosition(15);

        expectedEx.expect(InsufficientParameterException.class);
        expectedEx.expectMessage("operator * (position: 15): insufficient parameters");

        when(numbers.size()).thenReturn(1);
        command.execute();
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        numbers.push(0.5);
        numbers.push(10.0);

        command.execute();
        assertEquals("5", numbers.toString());

        command.undo();
        assertEquals("0.5 10", numbers.toString());
    }
}
