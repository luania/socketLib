package com.luania.socketLib.demo.server;


import com.luania.socketLib.demo.CustomHead;
import com.luania.socketLib.server.SocketServerDelegate;

public class Main {
    public static void main(String[] args) {
        CustomServer customServer = new CustomServer(3000, new SocketServerDelegate<CustomHead>() {
            @Override
            public String handle(CustomHead customHead, String s) {
                return "custom";
            }
        });
        customServer.start();
    }
}
