package com.lujiahao.l_library.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @describe IO相关的工具类
 * @author lujiahao
 * created at 2016/5/10 14:21
 */
public class IOUtils {
    /**
     * 关流操作
     * @param closeable
     */
    public static void close(Closeable closeable){
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
