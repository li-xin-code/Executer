package com.lx;

/**
 * Class Modify
 *
 * @author lixin
 */
public class ClassModifier {
    /**
     * class文件前8位分别是 魔数（Magic Number）和class文件版本号 各占4位
     * 第9位 是常量池的容量（容量22表示有21项常量）
     */
    private static final int CONSTANT_POOL_INDEX = 8;

    /**
     * CONSTANT_Utf8_info型常量标志位
     * 补充：CONSTANT_Utf8_info型常量的结构
     * 类型   名称    数量
     * u1    tag     1
     * u2    length  1
     * u1    bytes   length
     */
    private static final int CONSTANT_UTF8_INFO = 1;
    /**
     * 这里处理标志0～12
     * 3  4  5  6  7  8  9  10 11 12
     * 5  5  9  9  3  3  5  5  5  5
     * 标志 0 2 没有对应类型，标志1为 CONSTANT_Utf8_info除外因为他不定长。
     */
    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};
    /**
     * 对应u1类型长度为1
     */
    private static final int U1 = 1;
    /**
     * 对应u2类型长度为2
     */
    private static final int U2 = 2;

    private byte[] classBytes;

    public ClassModifier(byte[] classBytes) {
        this.classBytes = classBytes;
    }

    public byte[] modifyUtf8Constant(String oldStr, String newStr) {
        int cpc = getConstantPoolCont();
        int offset = CONSTANT_POOL_INDEX + U2;
        for (int i = 0; i < cpc; i++) {
            // tag 常量类型标志
            int tag = ByteUtils.bytes2Int(classBytes, offset, U1);
            if (tag == CONSTANT_UTF8_INFO) {
                int len = ByteUtils.bytes2Int(classBytes, offset + U1, U2);
                offset += U1 + U2;
                String str = ByteUtils.bytes2String(classBytes, offset, len);
                if (str.equalsIgnoreCase(oldStr)) {
                    byte[] newStrBytes = ByteUtils.string2Bytes(newStr);
                    byte[] newStrLenBytes = ByteUtils.int2Bytes(newStr.length(), U2);
                    classBytes = ByteUtils.bytesReplace(classBytes, offset - U2, U2, newStrLenBytes);
                    classBytes = ByteUtils.bytesReplace(classBytes, offset, len, newStrBytes);
                    return classBytes;
                }
                offset += len;
            } else {
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classBytes;
    }

    public int getConstantPoolCont() {
        return ByteUtils.bytes2Int(classBytes, CONSTANT_POOL_INDEX, U2);
    }
}
