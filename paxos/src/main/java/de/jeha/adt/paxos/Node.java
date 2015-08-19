package de.jeha.adt.paxos;

import de.jeha.adt.paxos.basic.BasicAcceptor;
import de.jeha.adt.paxos.basic.BasicProposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public class Node implements Proposer, Acceptor {

    private static final Logger LOG = LoggerFactory.getLogger(Node.class);

    private final String uid;
    private final Messenger messenger;
    private final Acceptor acceptor;
    private final Proposer proposer;

    public Node(String uid, Messenger messenger) {
        this.uid = uid;
        this.messenger = messenger;
        this.acceptor = new BasicAcceptor(uid, messenger);
        this.proposer = new BasicProposer(uid, messenger);
    }

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
        proposer.setProposalValue( proposalValue);
    }

}
