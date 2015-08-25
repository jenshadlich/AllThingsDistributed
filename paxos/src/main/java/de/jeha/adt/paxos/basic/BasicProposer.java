package de.jeha.adt.paxos.basic;

import de.jeha.adt.paxos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jenshadlich@googlemail.com
 */
public class BasicProposer implements Proposer {

    private static final Logger LOG = LoggerFactory.getLogger(BasicProposer.class);

    private final String uid;
    private final Messenger messenger;

    private Proposal proposal = new Proposal();
    private Proposal acceptedProposal;

    private Set<String> receivedPromises = new HashSet<>();
    private final int quorumSize = 3;

    public BasicProposer(String uid, Messenger messenger) {
        this.uid = uid;
        this.messenger = messenger;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public void prepare() {
        LOG.debug("[uid = {}] prepare", uid);

        proposal.incrementProposalNumber();

        LOG.debug("[uid = {}] send prepare {}", uid, proposal.getProposalNumber());
        messenger.sendPrepare(uid, proposal.getProposalNumber());
    }

    @Override
    public void receivePromise(String fromUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal) {
        LOG.debug("[uid = {}] received promise {} from {}", uid, proposalNumber, fromUid);

        if (!this.proposal.getProposalNumber().isEqual(proposalNumber)) {
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

            if (previousAcceptedProposal != null) {
                LOG.debug("[uid = {}] apply previous accepted proposal {} from {}", uid, proposalNumber, fromUid);

                acceptedProposal = previousAcceptedProposal;
            }
        }

        // if quorum size if reached
        if (receivedPromises.size() >= quorumSize) {
            messenger.sendAccept(uid, proposal);
        }
    }

    @Override
    public void receiveAccepted(String fromUid, Proposal acceptedProposal) {
        LOG.debug("[uid = {}] received accepted {} from {}", uid, acceptedProposal, fromUid);
    }

    public void setProposalValue(ProposalValue proposalValue) {
        proposal.setProposalValue(proposalValue);
    }

}
