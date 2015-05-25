package de.jeha.adt.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public class Proposal {

    private ProposalNumber proposalNumber;
    private ProposalValue proposalValue;

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

    public void setProposalNumber(ProposalNumber proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public void setProposalValue(ProposalValue proposalValue) {
        this.proposalValue = proposalValue;
    }
}
