package com.lx;

/**
 * bytes 数组处理工具
 *
 * @author lixin
 */
public class ByteUtils {

    public static int bytes2Int(byte[] bytes, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int n = ((int) bytes[i]) & 0xff;
            n <<= (--len) * 8;
            sum += n;
        }
        return sum;
    }

    public static byte[] int2Bytes(int value, int len) {
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            bytes[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return bytes;
    }

    public static String bytes2String(byte[] bytes, int start, int len) {
        return new String(bytes, start, len);
    }

    public static byte[] string2Bytes(String s) {
        return s.getBytes();
    }

    public static byte[] bytesReplace(byte[] original, int offset, int len, byte[] replace) {
        byte[] newBytes = new byte[original.length + replace.length - len];
        System.arraycopy(original, 0, newBytes, 0, offset);
        System.arraycopy(replace, 0, newBytes, offset, replace.length);
        System.arraycopy(original, offset + len,
                newBytes, offset + replace.length,
                original.length - offset - len);
        return newBytes;
    }

}
