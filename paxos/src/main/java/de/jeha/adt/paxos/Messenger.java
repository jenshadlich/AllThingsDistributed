package de.jeha.adt.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public interface Messenger {

    void sendPrepare(String fromUid, ProposalNumber proposalNumber);

    void sendPromise(String proposerUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal);

    void sendAccept(Proposal proposal);

    void sendAccepted(Proposal acceptedProposal);

    void addNode(Node node);
}
