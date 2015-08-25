package de.jeha.adt.paxos.basic;

import de.jeha.adt.paxos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public class BasicAcceptor implements Acceptor {

    private static final Logger LOG = LoggerFactory.getLogger(BasicAcceptor.class);

    private final String uid;
    private final Messenger messenger;

    private Proposal promise = new Proposal(new ProposalNumber(), null);
    private Proposal acceptedProposal;

    public BasicAcceptor(String uid, Messenger messenger) {
        this.uid = uid;
        this.messenger = messenger;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public void receivePrepare(String fromUid, ProposalNumber proposalNumber) {
        LOG.debug("[uid = {}] receive prepare from {}", uid, fromUid);

        if (proposalNumber.isGreaterThan(promise.getProposalNumber())) {
            promise.setProposalNumber(proposalNumber);
        }

        // always return a promise
        messenger.sendPromise(uid, proposalNumber, acceptedProposal);
    }

    @Override
    public void receiveAccept(String fromUid, Proposal proposal) {
        LOG.debug("[uid = {}] receive accept from {}", uid, fromUid);

        if (proposal.getProposalNumber().isGreaterThanEquals(this.promise.getProposalNumber())) {
            promise.setProposalNumber(proposal.getProposalNumber());
            acceptedProposal = proposal;

            messenger.sendAccepted(acceptedProposal);
        }
    }

}
