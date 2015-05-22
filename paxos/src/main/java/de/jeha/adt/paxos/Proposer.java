package de.jeha.adt.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public interface Proposer {

    public void prepare();

    public void receivePromise(String fromUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal);

}
