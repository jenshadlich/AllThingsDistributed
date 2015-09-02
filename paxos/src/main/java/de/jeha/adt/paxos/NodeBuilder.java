package de.jeha.adt.paxos;

/**
 * @author jenshadlich@googlemail.com
 */
public class NodeBuilder {

    private String uid;
    private Messenger messenger;

    private NodeBuilder() {

    }

    public static NodeBuilder basic() {
        return new NodeBuilder();
    }

    public Node build() {
        return new Node(uid, messenger);
    }

    public NodeBuilder withUid(String uid) {
        this.uid = uid;
        return this;
    }

    public NodeBuilder withMessenger(Messenger messenger) {
        this.messenger = messenger;
        return this;
    }

}