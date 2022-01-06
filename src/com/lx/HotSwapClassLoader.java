package com.lx;

/**
 * @author lixin
 */
public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class<?> loadClassByByte(byte[] bytes) {
        return super.defineClass(null, bytes, 0, bytes.length);
    }
}
