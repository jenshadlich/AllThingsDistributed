package de.jeha.atd.crdt;

/**
 * @author jenshadlich@googlemail.com
 */
public interface CRDT<V, C extends CRDT<V, C>> {

    V value();

    C merge(C other);

    String serialize();

}
