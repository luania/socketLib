package com.luania.socketLib.client;

import com.luania.socketLib.SocketIO;
import com.luania.socketLib.contract.Head;
import com.luania.socketLib.contract.SocketContract;
import com.luania.socketLib.client.listener.SocketClientListener;
import com.luania.socketLib.client.listener.SocketClientListenerSet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Administrator on 2018/1/5.
 */

public abstract class SocketClient<H extends Head> implements Runnable {
    private H head;
    private String message;
    private SocketClientListenerSet<H> listeners = new SocketClientListenerSet<H>();
    private SocketContract<H> contract;

    public abstract String serverHost();
    public abstract int serverPort();
    public abstract int timeout();

    public abstract SocketContract<H> getContract();

    public SocketClient(H head, String message) {
        this.message = message;
        this.head = head;
        this.contract = getContract();
    }

    public SocketClient<H> addListener(SocketClientListener<H> listener) {
        this.listeners.add(listener);
        return this;
    }

    public SocketClient<H> removeListener(SocketClientListener<H> listener) {
        this.listeners.remove(listener);
        return this;
    }

    @Override
    public void run() {
        listeners.start();
        String serverHost = serverHost();
        int serverPort = serverPort();

        InetSocketAddress socketAdd = new InetSocketAddress(serverHost, serverPort);
        Socket socket = new Socket();
        SocketIO socketIO;
        try {
            socket.connect(socketAdd, timeout());
            socketIO = new SocketIO(socket, contract.encoding());
            listeners.connSuccess();
        } catch (IOException e) {
            listeners.connFail(e);
            listeners.onFinal();
            return;
        }
        try {
            String send = contract.getSendMsg(head, message);
            listeners.sendBegin(send);
            socketIO.send(send);
            String strHead = socketIO.recv(contract.headLength());
            H responseHead = contract.parseHead(strHead);
            String body = socketIO.recv(responseHead.getBodyLength());
            listeners.success(responseHead, body);
        } catch (IOException e) {
            listeners.ioFail(e);
        } finally {
            try {
                socketIO.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            listeners.onFinal();
        }
    }
}