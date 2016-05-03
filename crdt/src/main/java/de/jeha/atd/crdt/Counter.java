package de.jeha.atd.crdt;

/**
 * @author jenshadlich@googlemail.com
 */
public class Counter {

    private int value = 0;

    private Counter(int value) {
        this.value = value;
    }

    public static Counter New() {
        return new Counter(0);
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
