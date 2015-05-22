package de.jeha.adt.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public interface Acceptor {

    public void receivePrepare(String fromUid, ProposalNumber proposalNumber);

    public void receiveAccept(String fromUid, Proposal proposal);
    
}
