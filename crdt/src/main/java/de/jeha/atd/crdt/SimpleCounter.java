package de.jeha.atd.crdt;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimpleCounter {

    private int value = 0;

    private SimpleCounter(int value) {
        this.value = value;
    }

    public static SimpleCounter New() {
        return new SimpleCounter(0);
    }

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    public int getValue() {
        return value;
    }

}
