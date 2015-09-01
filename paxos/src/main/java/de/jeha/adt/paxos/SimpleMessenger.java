package de.jeha.adt.paxos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimpleMessenger implements Messenger {

    private final List<Acceptor> acceptors = new ArrayList<>();
    private final List<Proposer> proposers = new ArrayList<>();

    @Override
    public void sendPrepare(String fromUid, ProposalNumber proposalNumber) {
        acceptors.stream()
                .forEach(acceptor -> acceptor.receivePrepare(fromUid, proposalNumber));
    }

    @Override
    public void sendPromise(String fromUid, String toUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal) {
        // send to proposer only
        proposers.stream()
                .filter(proposer -> proposer.getUid().equals(toUid))
                .forEach(proposer -> proposer.receivePromise(fromUid, proposalNumber, previousAcceptedProposal));
    }

    @Override
    public void sendAccept(String fromUid, Proposal proposal) {
        acceptors.stream()
                .forEach(acceptor -> acceptor.receiveAccept(fromUid, proposal));
    }

    @Override
    public void sendAccepted(String fromUid, String toUid, Proposal acceptedProposal) {
        // send to proposer only
        proposers.stream()
                .filter(proposer -> proposer.getUid().equals(toUid))
                .forEach(node -> node.receiveAccepted(fromUid, acceptedProposal));
    }

    @Override
    public void addAcceptor(Acceptor acceptor) {
        acceptors.add(acceptor);
    }

    @Override
    public void addProposer(Proposer proposer) {
        proposers.add(proposer);
    }

}
