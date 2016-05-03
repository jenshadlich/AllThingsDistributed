package de.jeha.atd.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public interface Proposer extends Identifiable {

    void prepare();

    void receivePromise(String fromUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal);

    void setProposalValue(ProposalValue proposalValue);

    void receiveAccepted(String fromUid, Proposal acceptedProposal);

}
