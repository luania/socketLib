package com.luania.socketLib;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketIO {
    private InputStream is;
    private OutputStream os;
    private Socket socket;
    private String encoding;

    public SocketIO(Socket socket, String encoding) throws IOException {
        this.socket = socket;
        this.is = socket.getInputStream();
        this.os = socket.getOutputStream();
        this.encoding = encoding;
    }

    public boolean send(String msg) throws IOException {
        if (isClosed()) {
            return false;
        }
        final byte[] bytes = msg.getBytes(encoding);
        BufferedToDo.start(bytes.length, new BufferedToDo.Listener() {
            @Override
            public void doOnce(int offset, int doLength) throws IOException {
                os.write(bytes, offset, doLength);
            }
        });
        return true;
    }

    public String recv(int msgLen) throws IOException {
        if (isClosed()) {
            return "";
        }
        final byte[] bytes = new byte[msgLen];
        BufferedToDo.start(msgLen, new BufferedToDo.Listener() {
            @Override
            public void doOnce(int offset, int doLength) throws IOException {
                is.read(bytes, offset, doLength);
            }
        });
        return new String(bytes, encoding);
    }

    public void close() throws IOException {
        if (os != null) {
            os.close();
        }
        if (is != null) {
            is.close();
        }
        if (socket != null) {
            socket.close();
        }
        socket = null;
    }

    public boolean isClosed(){
        return socket == null || socket.isClosed();
    }
}
