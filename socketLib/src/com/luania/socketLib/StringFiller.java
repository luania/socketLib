package com.luania.socketLib;

import java.io.UnsupportedEncodingException;

public class StringFiller {
    /*
     * fill String with " " at right
     */
    public static String left(String str, int intSize, String encoding) throws UnsupportedEncodingException {
        str = (str == null ? "" : str);
        return str + getFillStr(str, intSize, encoding);
    }

    /*
     * fill String with " " at left
     */
    public static String right(String str, int intSize, String encoding) throws UnsupportedEncodingException {
        str = (str == null ? "" : str);
        return  getFillStr(str, intSize, encoding) + str;
    }

    private static String getFillStr(String str, int intSize, String encoding) throws UnsupportedEncodingException {
        byte[] arr = str.getBytes(encoding);
        if (arr.length >= intSize) {
            return new String(arr, 0, intSize);
        }
        byte[] arrFill = new byte[intSize - arr.length];
        for (int i = 0; i < arrFill.length; ++i) {
            arrFill[i] = (byte) ' ';
        }
        return new String(arrFill);
    }
}
