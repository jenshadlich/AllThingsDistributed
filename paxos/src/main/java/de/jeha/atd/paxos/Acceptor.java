package de.jeha.atd.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public interface Acceptor extends Identifiable {

    void receivePrepare(String fromUid, ProposalNumber proposalNumber);

    void receiveAccept(String fromUid, Proposal proposal);

}
