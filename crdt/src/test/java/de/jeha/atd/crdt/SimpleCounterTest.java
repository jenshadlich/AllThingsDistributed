package de.jeha.atd.crdt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimpleCounterTest {

    @Test
    public void test() {
        SimpleCounter counter = SimpleCounter.New();

        counter.increment();
        counter.increment();
        counter.decrement();

        assertEquals(1, counter.getValue());
    }

}
