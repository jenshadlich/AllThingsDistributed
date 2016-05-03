package de.jeha.atd.crdt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class CounterTest {

    @Test
    public void test() {
        Counter counter = Counter.New();

        counter.increment();
        counter.increment();
        counter.decrement();

        assertEquals(1, counter.getValue());
    }

}
