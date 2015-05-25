package de.jeha.adt.paxos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jenshadlich@googlemail.com
 */
public class Node implements Proposer, Acceptor {

    private static final Logger LOG = LoggerFactory.getLogger(Node.class);

    private final String uid;
    private final Messenger messenger;

    private Proposal proposal = new Proposal(new ProposalNumber(), null);
    private Proposal acceptedProposal;

    private Set<String> receivedPromises = new HashSet<>();
    private final int quorumSize = 3;

    public Node(String uid, Messenger messenger) {
        this.uid = uid;
        this.messenger = messenger;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public void prepare() {
        proposal.getProposalNumber().increment();
        messenger.sendPrepare(uid, proposal.getProposalNumber());
    }

    @Override
    public void receivePromise(String fromUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal) {

        LOG.debug("received promise from {}", fromUid);

        if (this.proposal.getProposalNumber().isEqual(proposalNumber)) {
            return;
        }

        if (receivedPromises.contains(fromUid)) {
            return;
        }

        // add promises
        receivedPromises.add(fromUid);

        // check that any acceptor had previously accepted any proposal
        if (acceptedProposal == null
                || previousAcceptedProposal.getProposalNumber().isGreaterThan(acceptedProposal.getProposalNumber())) {
            acceptedProposal = previousAcceptedProposal;
        }

        // if quorum size if reached
        if (receivedPromises.size() >= quorumSize) {
            messenger.sendAccept(proposal);
        }
    }

    @Override
    public void receivePrepare(String fromUid, ProposalNumber proposalNumber) {

        if (proposalNumber.isGreaterThan(this.proposal.getProposalNumber())) {
            this.proposal.setProposalNumber(proposalNumber);
        }

        // always return a promise
        messenger.sendPromise(fromUid, proposalNumber, acceptedProposal);
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
