package com.alankzh.domain;

public enum OffWorkTypeEnum{
    unClocking(0),//0、未打卡
    normal(1),//1、正常下班
    leaveEarly(2),//2、早退
    absent(3),//3、旷班
    sickLeave(4),//4、病假
    personalLeave(5),//事假
    statutoryHoliday(6),//法定假日
    unDefine(10);//未定义的
    
    private int type;
    private OffWorkTypeEnum(int type) {
        this.type=type;
    }
    public int getType() {
        return type;
    }
    public static OffWorkTypeEnum valueOf(int value) {    //    手写的从int到enum的转换函数  
        switch (value) {  
        case 0:  
            return unClocking;  
        case 1:  
            return normal;
        case 2:  
            return leaveEarly;
        case 3:  
            return absent;
        case 4:  
            return sickLeave;
        case 5:  
            return personalLeave;
        case 6:  
            return statutoryHoliday;
        default:  
            return unDefine;  
        }  
    } 
}
