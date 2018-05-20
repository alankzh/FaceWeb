package com.alankzh.util;

import java.util.UUID;

import com.alankzh.annotation.Immutable;

@Immutable
public class UuidUtil {
    
    /**
     * 返回唯一标识，去掉其中的 “-” 符号
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
