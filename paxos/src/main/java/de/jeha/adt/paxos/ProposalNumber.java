package de.jeha.adt.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public class ProposalNumber {

    private long number = 0L;

    public void increment() {
        number++;
    }

    public boolean isEqual(ProposalNumber other) {
        return number == other.number;
    }

    public boolean isGreaterThan(ProposalNumber other) {
        return number > other.number;
    }

    public boolean isGreaterThanEquals(ProposalNumber other) {
        return number >= other.number;
    }

}
