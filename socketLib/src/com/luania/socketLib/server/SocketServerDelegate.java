package com.luania.socketLib.server;

import com.luania.socketLib.contract.Head;

/**
 * socket处理的代理
 *
 * @author luhuajie
 */
public interface SocketServerDelegate<H extends Head> {
    /**
     * 处理socket发来的信息
     *
     * @param head    报文头
     * @param body    报文体
     * @return 要返回的信息
     */
    String handle(H head, String body);
}