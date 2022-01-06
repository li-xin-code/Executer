package com.lx;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * 劫持java.lang.System
 *
 * @author lixin
 */
public class HockSystem {
    private static final InputStream in = System.in;

    private static final ByteArrayOutputStream buffer =
            new ByteArrayOutputStream();

    public static final PrintStream out = new PrintStream(buffer);

    public static final PrintStream err = out;

    public static String getBufferString() {
        return buffer.toString();
    }

    public static void clearBuffer() {
        buffer.reset();
    }

    public static void setSecurityManager(final SecurityManager manager) {
        System.setSecurityManager(manager);
    }

    public static SecurityManager getSystemManager() {
        return System.getSecurityManager();
    }

    public static void arraycopy(Object src, int srcPos,
                                 Object dest, int destPos,
                                 int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static int identityHashCode(Object x) {
        return System.identityHashCode(x);
    }

    public static void main(String[] args) {
        HockSystem.out.println("x");
    }

}
