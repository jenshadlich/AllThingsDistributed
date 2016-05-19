package de.jeha.atd.crdt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimpleCounter2Test {

    @Test
    public void test() {
        SimpleCounter2 counter = SimpleCounter2.New();

        counter.increment();
        counter.increment();
        counter.decrement();

        assertEquals(1, counter.getValue());
    }

}
