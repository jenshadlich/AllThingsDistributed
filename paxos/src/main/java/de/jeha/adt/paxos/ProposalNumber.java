package de.jeha.adt.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public class ProposalNumber {

    private final long number;

    public ProposalNumber() {
        number = 0L;
    }

    private ProposalNumber(long number) {
        this.number = number;
    }

    public ProposalNumber increment() {
        return new ProposalNumber(number + 1);
    }

    public boolean isEqual(ProposalNumber other) {
        return number == other.number;
    }

    public boolean isZero() {
        return number == 0L;
    }

    public boolean isGreaterThan(ProposalNumber other) {
        return number > other.number;
    }

    public boolean isGreaterThanEquals(ProposalNumber other) {
        return number >= other.number;
    }

    @Override
    public String toString() {
        return "ProposalNumber{" +
                "number=" + number +
                '}';
    }

}
