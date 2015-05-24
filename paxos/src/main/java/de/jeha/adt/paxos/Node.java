package de.jeha.adt.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public class Node implements Proposer, Acceptor {

    private final String uid;
    private final Messenger messenger;
    private ProposalNumber proposalNumber = new ProposalNumber();
    private Proposal acceptedProposal;

    public Node(String uid, Messenger messenger) {
        this.uid = uid;
        this.messenger = messenger;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public void prepare() {
        proposalNumber.increment();
        messenger.sendPrepare(uid, proposalNumber);
    }

    @Override
    public void receivePromise(String fromUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal) {
        // TODO
        throw new NotYetImplementedException();
    }

    @Override
    public void receivePrepare(String fromUid, ProposalNumber proposalNumber) {

        if (proposalNumber.isGreaterThan(this.proposalNumber)) {
            this.proposalNumber = proposalNumber;
        }

        // always return a promise
        messenger.sendPromise(fromUid, proposalNumber, acceptedProposal);
    }

    @Override
    public void receiveAccept(String fromUid, Proposal proposal) {
        // TODO
        throw new NotYetImplementedException();
    }

}
