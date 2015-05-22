package de.jeha.adt.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public class Proposal {

    private final ProposalNumber proposalNumber;
    private final ProposalValue proposalValue;

    public Proposal(ProposalNumber proposalNumber, ProposalValue proposalValue) {
        this.proposalNumber = proposalNumber;
        this.proposalValue = proposalValue;
    }

    public ProposalNumber getProposalNumber() {
        return proposalNumber;
    }

    public ProposalValue getProposalValue() {
        return proposalValue;
    }

}
