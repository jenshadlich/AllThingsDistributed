package de.jeha.adt.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public interface Messenger {

    public void sendPrepare(String fromUid, ProposalNumber proposalNumber);

    public void sendPromise(String proposerUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal);

    public void sendAccept(Proposal proposal);

    public void sendAccepted(Proposal acceptedProposal);

    void addNode(Node node);
}
