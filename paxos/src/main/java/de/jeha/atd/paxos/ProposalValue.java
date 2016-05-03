package de.jeha.atd.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public class ProposalValue {

    private final String value;

    public ProposalValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ProposalValue{" +
                "value='" + value + '\'' +
                '}';
    }

}
