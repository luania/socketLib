package com.luania.socketLib;

import java.io.IOException;

public class BufferedToDo {
    private static final int BUFFER_LENGTH = 512;

    public static void start(int total, Listener bufferedToDoListener) throws IOException {
        int offset = 0;
        while (offset < total) {
            int left = total - offset;
            if (left < BUFFER_LENGTH) {
                bufferedToDoListener.doOnce(offset, left);
                break;
            }
            bufferedToDoListener.doOnce(offset, BUFFER_LENGTH);
            offset += BUFFER_LENGTH;
        }
    }

    interface Listener {
        void doOnce(int offset, int doLength) throws IOException;
    }
}