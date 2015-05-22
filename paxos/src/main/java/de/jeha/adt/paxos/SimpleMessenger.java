package de.jeha.adt.paxos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class SimpleMessenger implements Messenger {

    private final List<Node> nodes = new ArrayList<>();

    @Override
    public void sendPrepare(String fromUid, ProposalNumber proposalNumber) {
        for (Node node : nodes) {
            node.receivePrepare(fromUid, proposalNumber);
        }
    }

    @Override
    public void sendPromise(String proposerUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal) {
        // TODO
        throw new NotYetImplementedException();
    }

    @Override
    public void sendAccept(Proposal proposal) {
        // TODO
        throw new NotYetImplementedException();
    }

    @Override
    public void sendAccepted(Proposal acceptedProposal) {
        // TODO
        throw new NotYetImplementedException();
    }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
    }
}
