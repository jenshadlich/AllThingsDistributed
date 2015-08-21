package de.jeha.adt.paxos;

import de.jeha.adt.paxos.basic.BasicAcceptor;
import de.jeha.adt.paxos.basic.BasicProposer;

/**
 * @author jenshadlich@googlemail.com
 */
public class Node implements Proposer, Acceptor {

    private final Acceptor acceptor;
    private final Proposer proposer;

    public Node(String uid, Messenger messenger) {
        this.acceptor = new BasicAcceptor(uid, messenger);
        this.proposer = new BasicProposer(uid, messenger);
    }

    @Override
    public void prepare() {
        proposer.prepare();
    }

    @Override
    public void receivePromise(String fromUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal) {
        proposer.receivePromise(fromUid, proposalNumber, previousAcceptedProposal);
    }

    @Override
    public void receivePrepare(String fromUid, ProposalNumber proposalNumber) {
        acceptor.receivePrepare(fromUid, proposalNumber);
    }

    @Override
    public void receiveAccept(String fromUid, Proposal proposal) {
        acceptor.receiveAccept(fromUid, proposal);
    }

    @Override
    public void setProposalValue(ProposalValue proposalValue) {
        proposer.setProposalValue(proposalValue);
    }

}
