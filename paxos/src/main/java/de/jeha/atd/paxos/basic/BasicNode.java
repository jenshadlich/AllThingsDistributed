package de.jeha.atd.paxos.basic;

import de.jeha.atd.paxos.*;

/**
 * @author jenshadlich@googlemail.com
 */
public class BasicNode implements Node {

    private final String uid;
    private final Acceptor acceptor;
    private final Proposer proposer;

    public BasicNode(String uid, Messenger messenger) {
        this.uid = uid;
        this.acceptor = new BasicAcceptor(uid, messenger);
        this.proposer = new BasicProposer(uid, messenger);
    }

    @Override
    public String getUid() {
        return uid;
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

    @Override
    public void receiveAccepted(String fromUid, Proposal acceptedProposal) {
        proposer.receiveAccepted(fromUid, acceptedProposal);
    }

}
