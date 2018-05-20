package com.alankzh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.alankzh.constant.Constant;


public class HolidayUtil {
    /**
     * 公共接口每天只能使用1000次，故将其结果缓存后使用
     */
    private static boolean holiday=false;
    
    /**
     * 根据一个公共接口，判断是否一个日期是否是假期
     * @param date
     * @return
     * 0 为工作日
     * 1 为休息日
     * 2 为节假日
     * @throws IOException
     */
    public static int isHoliday(LocalDate date) throws IOException {
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyyMMdd");
        
        System.out.println(Constant.HOLIDAY_JUDGE_URL+date.format(dateTimeFormatter));
        
        URL url=new URL(Constant.HOLIDAY_JUDGE_URL+date.format(dateTimeFormatter));
        HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
        
        httpURLConnection.connect();
        InputStream is = httpURLConnection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String strRead = null;
        StringBuilder stringBuilder=new StringBuilder();
        while ((strRead = reader.readLine()) != null) {
            stringBuilder.append(strRead);
        }
        reader.close();
        int result=Integer.parseInt(stringBuilder.toString());
        System.out.println(result);
        return result;
    }
    
    public static void setCacheHoliday(boolean holiday) {
        HolidayUtil.holiday=holiday;
    }
    
    public static boolean getCacheHoliday() {
        return HolidayUtil.holiday;
    }
}
