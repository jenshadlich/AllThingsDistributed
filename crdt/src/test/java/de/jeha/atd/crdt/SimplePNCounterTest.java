package de.jeha.atd.crdt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimplePNCounterTest {

    @Test
    public void test() {
        SimplePNCounter counter = SimplePNCounter.New();

        counter.increment();
        counter.increment();
        counter.decrement();

        assertEquals(1, counter.getValue());
    }

}
