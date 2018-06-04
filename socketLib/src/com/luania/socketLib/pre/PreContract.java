package com.luania.socketLib.pre;

import com.luania.socketLib.StringFiller;
import com.luania.socketLib.contract.SocketContract;

import java.io.UnsupportedEncodingException;

public class PreContract implements SocketContract<PreHead> {
    @Override
    public int headLength() {
        return 40;
    }

    @Override
    public String encoding() {
        return "GBK";
    }

    @Override
    public PreHead parseHead(String strHead) {
        return new PreHead(
            strHead.substring(0, 3).trim(),
            Integer.parseInt(strHead.substring(3, 11).trim())
        );
    }

    @Override
    public String getSendMsg(PreHead head, String message) {
        StringBuilder builder = new StringBuilder(headLength());
        try {
            builder.append(fs(head.getCommand(), 3));
            builder.append(fs(message.getBytes(encoding()).length + "", 8));
            builder.append(fs("", 29));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        builder.append(message);
        return builder.toString();
    }

    private String fs(String str, int length) throws UnsupportedEncodingException {
        return StringFiller.left(str, length, encoding());
    }
}
