package com.luania.socketLib.pre;

import com.luania.socketLib.contract.SocketContract;
import com.luania.socketLib.server.SocketServer;
import com.luania.socketLib.server.SocketServerDelegate;

public class PreServer extends SocketServer<PreHead> {
    public PreServer(int port, SocketServerDelegate<PreHead> socketDelegate) {
        super(port, socketDelegate);
    }

    @Override
    public SocketContract<PreHead> getContract() {
        return new PreContract();
    }
}
