package com.luania.socketLib.client.listener;

import com.luania.socketLib.contract.Head;

import java.io.IOException;
import java.util.HashSet;

public class SocketClientListenerSet<H extends Head> extends HashSet<SocketClientListener<H>> {
    public void sendBegin(String body) {
        for (SocketClientListener listener : this)
            listener.sendBegin(body);
    }

    public void start() {
        for (SocketClientListener listener : this)
            listener.onStart();
    }

    public void connSuccess() {
        for (SocketClientListener listener : this)
            listener.connSuccess();
    }

    public void success(H head, String body) {
        for (SocketClientListener listener : this)
            listener.success(head, body);
    }

    public void connFail(Exception e) {
        for (SocketClientListener listener : this)
            listener.connFail(e);
    }

    public void ioFail(IOException e) {
        for (SocketClientListener listener : this)
            listener.ioFail(e);
    }

    public void onFinal() {
        for (SocketClientListener listener : this)
            listener.onFinal();
    }
}