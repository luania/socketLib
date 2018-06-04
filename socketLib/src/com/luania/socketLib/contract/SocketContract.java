package com.luania.socketLib.contract;

public interface SocketContract<H extends Head> {
    int headLength();
    String encoding();
    H parseHead(String strHead);
    String getSendMsg(H head, String message);
}
