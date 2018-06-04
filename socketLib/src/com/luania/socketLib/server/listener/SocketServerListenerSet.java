package com.luania.socketLib.server.listener;

import com.luania.socketLib.contract.Head;

import java.io.IOException;
import java.util.HashSet;

public class SocketServerListenerSet<T extends Head> extends HashSet<SocketServerListener<T>> {
    public void serverStart(int port) {
        for(SocketServerListener listener:this)
            listener.serverStart(port);
    }

    public void serverError(Exception e) {
        for(SocketServerListener listener:this)
            listener.serverError(e);
    }

    public void serverKillError(Exception e) {
        for(SocketServerListener listener:this)
            listener.serverKillError(e);
    }

    public void serverKill() {
        for(SocketServerListener listener:this)
            listener.serverKill();
    }

    public void beforeExcute() {
        for(SocketServerListener listener:this)
            listener.beforeExcute();
    }

    public void excuteError(IOException e) {
        for(SocketServerListener listener:this)
            listener.excuteError();
    }

    public void excuteFinal() {
        for(SocketServerListener listener:this)
            listener.excuteFinal();
    }

    public void excuteReceived(T head, String body) {
        for(SocketServerListener listener:this)
            listener.excuteReceived(head, body);
    }

    public void beforeExcuteReturn(String send) {
        for(SocketServerListener listener:this)
            listener.beforeRespond(send);
    }

    public void afterExcuteReturn(String send) {
        for(SocketServerListener listener:this)
            listener.afterRespond(send);
    }
}
