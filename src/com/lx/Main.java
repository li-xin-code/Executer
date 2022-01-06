package com.lx;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author lixin
 */
public class Main {
    public static void main(String[] args) {
        // class文件路径
        String filePath = "/.../Task.class";
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            byte[] bytes = new byte[inputStream.available()];
            if (inputStream.read(bytes) != bytes.length) {
                System.out.println("Number of bytes not as expected");
            }
            String result = JavaClassExecuter.execute(bytes);
            System.out.println("result:" + result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
