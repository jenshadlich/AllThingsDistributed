package de.jeha.atd.paxos;

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
                NodeBuilder.basic().withUid("1").withMessenger(messenger).build(),
                NodeBuilder.basic().withUid("2").withMessenger(messenger).build(),
                NodeBuilder.basic().withUid("3").withMessenger(messenger).build()
        );

        nodes.forEach(messenger::addAcceptor);
        nodes.forEach(messenger::addProposer);

        Proposer proposer = nodes.get(0);

        proposer.setProposalValue(new ProposalValue("foobar"));
        proposer.prepare();
    }

}
