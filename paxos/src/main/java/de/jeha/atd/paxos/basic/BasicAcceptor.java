package de.jeha.atd.paxos.basic;

import de.jeha.atd.paxos.Acceptor;
import de.jeha.atd.paxos.Messenger;
import de.jeha.atd.paxos.Proposal;
import de.jeha.atd.paxos.ProposalNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jenshadlich@googlemail.com
 */
public class BasicAcceptor implements Acceptor {

    private static final Logger LOG = LoggerFactory.getLogger(BasicAcceptor.class);

    private final String uid;
    private final Messenger messenger;

    private Proposal promise = new Proposal();
    private Proposal acceptedProposal;

    public BasicAcceptor(String uid, Messenger messenger) {
        this.uid = uid;
        this.messenger = messenger;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void receivePrepare(String fromUid, ProposalNumber proposalNumber) {
        LOG.debug("[uid = {}] receive prepare from {}", uid, fromUid);

        if (proposalNumber.isGreaterThan(promise.getProposalNumber())) {
            LOG.debug("[uid = {}] apply {} from {}", uid, proposalNumber, fromUid);

            promise.setProposalNumber(proposalNumber);
        }

        // always return a promise
        messenger.sendPromise(uid, fromUid, proposalNumber, acceptedProposal);
    }

    @Override
    public void receiveAccept(String fromUid, Proposal proposal) {
        LOG.debug("[uid = {}] receive accept from {}", uid, fromUid);

        if (proposal.getProposalNumber().isZero() || proposal.getProposalNumber().isGreaterThanEquals(promise.getProposalNumber())) {
            LOG.debug("[uid = {}] accept {} from {}", uid, proposal, fromUid);

            promise.setProposalNumber(proposal.getProposalNumber());
            acceptedProposal = proposal;

            messenger.sendAccepted(uid, fromUid, acceptedProposal);
        }
    }

}
