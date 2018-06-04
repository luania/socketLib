package com.luania.socketLib.client.listener;

import com.luania.socketLib.contract.Head;

import java.io.IOException;

public interface SocketClientListener<H extends Head> {
    void onStart();
    void connSuccess();
    void connFail(Exception e);
    void sendBegin(String body);
    void success(H head, String body);
    void ioFail(IOException e);
    void onFinal();
}
