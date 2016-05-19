package de.jeha.atd.crdt;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimpleCounter2 {

    private int increments = 0;
    private int decrements = 0;

    private SimpleCounter2(int value) {
        increments = value > 0 ? value : 0;
        decrements = value > 0 ? 0 : value;
    }

    public static SimpleCounter2 New() {
        return new SimpleCounter2(0);
    }

    public void increment() {
        increments++;
    }

    public void decrement() {
        decrements++;
    }

    public int getValue() {
        return increments - decrements;
    }

}
