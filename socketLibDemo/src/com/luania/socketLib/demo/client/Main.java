package com.luania.socketLib.demo.client;

import com.luania.socketLib.client.SocketClient;
import com.luania.socketLib.client.listener.SocketClientListenerAdapter;
import com.luania.socketLib.contract.SocketContract;
import com.luania.socketLib.demo.CustomHead;

public class Main {
    public static void main(String[] args) {
        final CustomClient socketClientEx = new CustomClient("H2", "asd");
        socketClientEx.addListener(new SocketClientListenerAdapter<CustomHead>(){
            @Override
            public void success(CustomHead head, String body) {
                super.success(head, body);
                System.out.println("success:a");
                System.out.println(head.getCommand() + ":" + body);
            }
        }).addListener(new SocketClientListenerAdapter<CustomHead>(){
            @Override
            public void success(CustomHead head, String body) {
                super.success(head, body);
                System.out.println("success:b");
                System.out.println(head.getCommand() + ":" + body);
            }
        });

        socketClientEx.run();
//        new Thread(socketClientEx).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                socketClientEx.run();
//            }
//        }).start();
    }
}
