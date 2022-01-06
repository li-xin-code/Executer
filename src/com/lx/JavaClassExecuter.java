package com.lx;

import java.lang.reflect.Method;

/**
 * @author lixin
 */
public class JavaClassExecuter {
    public static String execute(byte[] classBytes) {
        HockSystem.clearBuffer();
        ClassModifier modifier = new ClassModifier(classBytes);
        byte[] newClassBytes = modifier.modifyUtf8Constant("java/lang/System", "com/lx/HockSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class<?> clazz = loader.loadClassByByte(newClassBytes);
        try {
            Method method = clazz.getMethod("main", String[].class);
            method.invoke(null, (Object) null);
        }catch (Exception e){
            e.printStackTrace(HockSystem.out);
        }
        return HockSystem.getBufferString();
    }
}
