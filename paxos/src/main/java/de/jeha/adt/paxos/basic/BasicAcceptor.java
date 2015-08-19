package de.jeha.adt.paxos.basic;

import de.jeha.adt.paxos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jenshadlich@googlemail.com
 */
public class BasicAcceptor implements Acceptor {

    private static final Logger LOG = LoggerFactory.getLogger(BasicAcceptor.class);

    private final String uid;
    private final Messenger messenger;

    private Proposal proposal = new Proposal(new ProposalNumber(), null);
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

        if (proposalNumber.isGreaterThan(this.proposal.getProposalNumber())) {
            this.proposal.setProposalNumber(proposalNumber);
        }

        // always return a promise
        messenger.sendPromise(uid, proposalNumber, acceptedProposal);
    }

    @Override
    public void receiveAccept(String fromUid, Proposal proposal) {
        // TODO
        throw new NotYetImplementedException();
    }

    public void setProposalValue(ProposalValue proposalValue) {
        proposal.setProposalValue(proposalValue);
    }

}
