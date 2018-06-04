package com.luania.socketLib.server.listener;

import com.luania.socketLib.contract.Head;

public interface SocketServerListener<H extends Head> {
    void serverStart(int port);

    void serverError(Exception e);

    void serverKillError(Exception e);

    void serverKill();

    void beforeExcute();

    void excuteError();

    void excuteReceived(H head, String body);

    void beforeRespond(String send);

    void afterRespond(String send);

    void excuteFinal();
}
