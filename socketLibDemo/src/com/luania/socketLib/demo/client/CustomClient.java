package com.luania.socketLib.demo.client;

import com.luania.socketLib.client.SocketClient;
import com.luania.socketLib.contract.SocketContract;
import com.luania.socketLib.demo.CustomContract;
import com.luania.socketLib.demo.CustomHead;

public class CustomClient extends SocketClient<CustomHead> {
    public CustomClient(String command, String message) {
        super(new CustomHead(command), message);
        addListener(new CustomClientListenerLog());
    }

    @Override
    public String serverHost() {
        return "127.0.0.1";
    }

    @Override
    public int serverPort() {
        return 3000;
    }

    @Override
    public int timeout() {
        return 30*1000;
    }

    @Override
    public SocketContract<CustomHead> getContract() {
        return new CustomContract();
    }
}
