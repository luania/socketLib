package com.luania.socketLib.demo.client;

import com.luania.socketLib.client.listener.SocketClientListenerAdapter;
import com.luania.socketLib.demo.CustomHead;

public class CustomClientListenerProgress extends SocketClientListenerAdapter<CustomHead> {
    @Override
    public void onStart() {
        System.out.println("Progress: on");
        super.onStart();
    }

    @Override
    public void onFinal() {
        System.out.println("Progress: off");
        super.onFinal();
    }
}
