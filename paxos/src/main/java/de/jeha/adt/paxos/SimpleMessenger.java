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
    public void sendPromise(String fromUid, String toUid, ProposalNumber proposalNumber, Proposal previousAcceptedProposal) {
        for (Node node : nodes) {
            // send to proposer only
            if (node.getUid().equals(toUid)) {
                node.receivePromise(fromUid, proposalNumber, previousAcceptedProposal);
            }
        }
    }

    @Override
    public void sendAccept(String fromUid, Proposal proposal) {
        for (Node node : nodes) {
            node.receiveAccept(fromUid, proposal);
        }
    }

    @Override
    public void sendAccepted(String fromUid, String toUid, Proposal acceptedProposal) {
        for (Node node : nodes) {
            // send to proposer only
            if (node.getUid().equals(toUid)) {
                node.receiveAccepted(fromUid, acceptedProposal);
            }
        }
    }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
    }

}
