package com.alankzh.util;

import java.time.Duration;
import java.time.LocalTime;

import com.alankzh.annotation.Immutable;

@Immutable
public class LocalTimeCompareUtil {
    /**
     * 左边时间比右边早？
     * @param left
     * @param right
     * @return
     */
    public static boolean leftBeforeRight(LocalTime left,LocalTime right) {
        if(left.compareTo(right)>=0) {
            return false;
        }else {
            return true;
        }
    }
    
    /**
     * 返回左边时间到右边时间的毫秒数
     * 当左边时间比右边时间早时，会返回正值
     * @param from
     * @param to
     * @return
     */
    public static long getDurationSeconds(LocalTime from,LocalTime to) {
        Duration duration=Duration.between(from, to);
        return duration.getSeconds();
    }
}
