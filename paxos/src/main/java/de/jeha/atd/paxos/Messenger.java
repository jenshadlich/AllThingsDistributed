package de.jeha.atd.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public interface Messenger {

    void sendPrepare(String fromUid, ProposalNumber proposalNumber);

    void sendPromise(String proposerUid, String toUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal);

    void sendAccept(String fromUid, Proposal proposal);

    void sendAccepted(String fromUid, String toUid, Proposal acceptedProposal);

    void addAcceptor(Acceptor acceptor);

    void addProposer(Proposer proposer);

}
