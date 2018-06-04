package com.luania.socketLib.pre;

import com.luania.socketLib.client.SocketClient;
import com.luania.socketLib.contract.SocketContract;

public abstract class PreClient extends SocketClient<PreHead> {
    public PreClient(PreHead head, String message) {
        super(head, message);
    }

    public PreClient(String command, String message) {
        super(new PreHead(command), message);
    }

    @Override
    public SocketContract<PreHead> getContract() {
        return new PreContract();
    }
}
