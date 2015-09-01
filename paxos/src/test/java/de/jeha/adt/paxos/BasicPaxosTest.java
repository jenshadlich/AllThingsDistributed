package de.jeha.adt.paxos;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class BasicPaxosTest {

    @Test
    public void test() {
        Messenger messenger = new SimpleMessenger();

        List<Node> nodes = Arrays.asList(
                new Node("1", messenger),
                new Node("2", messenger),
                new Node("3", messenger)
        );

        messenger.addNode(nodes.get(0));
        messenger.addNode(nodes.get(1));
        messenger.addNode(nodes.get(2));

        Node proposer = nodes.get(0);

        proposer.setProposalValue(new ProposalValue("foobar"));
        proposer.prepare();
    }
}
