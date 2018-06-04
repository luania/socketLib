package com.luania.socketLib.server;

import com.luania.socketLib.SocketIO;
import com.luania.socketLib.contract.Head;
import com.luania.socketLib.contract.SocketContract;
import com.luania.socketLib.server.listener.SocketServerListener;
import com.luania.socketLib.server.listener.SocketServerListenerSet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * socket服务端
 *
 * @author luhuajie
 */
public abstract class SocketServer<H extends Head> extends Thread {
    private boolean living = true;
    private int port;
    private ServerSocket serverSocket = null;
    private ExecutorService executorService = null;// 线程池
    private SocketServerListenerSet<H> listeners = new SocketServerListenerSet();
    private SocketServerDelegate<H> socketDelegate;
    private SocketContract<H> contract;

    public SocketServer(int port, SocketServerDelegate<H> socketDelegate) {
        super();
        this.port = port;
        this.socketDelegate = socketDelegate;
        this.contract = getContract();
    }

    public abstract SocketContract<H> getContract();

    public void addListener(SocketServerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(SocketServerListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            listeners.serverStart(port);
            executorService = Executors.newCachedThreadPool();
            while (living) {
                final Socket socket = serverSocket.accept();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        excute(socket);
                    }
                });
            }
        } catch (Exception e) {
            listeners.serverError(e);
            kill();
        }
    }

    private void excute(Socket socket) {
        SocketIO socketIO = null;
        try {
            socketIO = new SocketIO(socket, contract.encoding());
            listeners.beforeExcute();
            String strHead = socketIO.recv(contract.headLength());

            H head = contract.parseHead(strHead);
            String body = socketIO.recv(head.getBodyLength());
            listeners.excuteReceived(head, body);

            String returnMsg = null;
            if (socketDelegate != null) {
                returnMsg = socketDelegate.handle(head, body);
                if(returnMsg == null){
                    returnMsg = "";
                }
            }
            String msg = contract.getSendMsg(head, returnMsg);
            listeners.beforeExcuteReturn(msg);
            if (returnMsg != null) {
                socketIO.send(msg);
            }
            listeners.afterExcuteReturn(msg);
        } catch (IOException e) {
            listeners.excuteError(e);
        } finally {
            try {
                socketIO.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            listeners.excuteFinal();
        }
    }

    public void kill() {
        living = false;
        listeners.serverKill();
        try {
            if (!serverSocket.isClosed()) {
                serverSocket.close();
            }
            serverSocket = null;
            executorService.shutdown();
        } catch (Exception e) {
            listeners.serverKillError(e);
        }
    }

    public boolean isLiving() {
        return living;
    }
}
