package com.luania.socketLib.demo.server;

import com.luania.socketLib.contract.SocketContract;
import com.luania.socketLib.demo.CustomContract;
import com.luania.socketLib.demo.CustomHead;
import com.luania.socketLib.server.SocketServer;
import com.luania.socketLib.server.SocketServerDelegate;

public class CustomServer extends SocketServer<CustomHead> {
    public CustomServer(int port, SocketServerDelegate<CustomHead> socketDelegate) {
        super(port, socketDelegate);
    }

    @Override
    public SocketContract<CustomHead> getContract() {
        return new CustomContract();
    }
}
