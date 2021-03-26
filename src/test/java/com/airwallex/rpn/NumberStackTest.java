package com.airwallex.rpn;

import org.junit.Test;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class NumberStackTest {

    @Test
    public void popAfterPush() {
        NumberStack numberStack = new NumberStack();
        numberStack.push(1.0);
        numberStack.push(10.0);
        assertEquals(10.0, numberStack.pop(), 0.0);
        assertEquals(1.0, numberStack.pop(), 0.0);
    }

    @Test(expected = EmptyStackException.class)
    public void popShouldFailIfStackIsEmpty() {
        NumberStack numberStack = new NumberStack();
        numberStack.pop();
    }

    @Test
    public void testClearStack() {
        NumberStack stack = new NumberStack();
        stack.push(1.0);
        assertFalse("stack should not be empty", stack.isEmpty());
        stack.clear();
        assertTrue("stack should be empty after cleared", stack.isEmpty());
    }

    @Test
    public void testCopyStack() {
        NumberStack numberStack = new NumberStack();
        numberStack.push(1.0);

        NumberStack stack2 = new NumberStack();
        stack2.push(0.0);
        stack2.push(10.0);

        numberStack.copyFrom(stack2);
        assertEquals(10.0, numberStack.pop(), 0.0);
        assertEquals(0.0, numberStack.pop(), 0.0);
        assertTrue("all numbers copied are popped out", numberStack.isEmpty());
    }

    @Test
    public void toStringShouldGenerateSpaceSeparatedNumbers() {
        NumberStack numberStack = new NumberStack();
        numberStack.push(1.0);
        numberStack.push(10.0);
        numberStack.push(0.0);

        assertEquals("1 10 0", numberStack.toString());
    }

    @Test
    public void shouldKeep10DecimalPlacesWhenDisplayed() {
        NumberStack numberStack = new NumberStack();
        numberStack.push(Math.sqrt(2));
        assertEquals("1.4142135624", numberStack.toString());
    }

    @Test
    public void shouldNotDisplayUnnecessaryZeros() {
        NumberStack numberStack = new NumberStack();
        numberStack.push(42d / 4);
        assertEquals("10.5", numberStack.toString());
    }

    @Test
    public void shouldOutputEmptyStringIfStackIsEmpty() {
        NumberStack numberStack = new NumberStack();
        assertEquals("", numberStack.toString());
    }
}
