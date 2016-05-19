package de.jeha.atd.crdt;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimplePNCounter {

    private int positive = 0;
    private int negative = 0;

    private SimplePNCounter(int value) {
        positive = value > 0 ? value : 0;
        negative = value > 0 ? 0 : value;
    }

    public static SimplePNCounter New() {
        return new SimplePNCounter(0);
    }

    public void increment() {
        positive++;
    }

    public void decrement() {
        negative++;
    }

    public int getValue() {
        return positive - negative;
    }

}
