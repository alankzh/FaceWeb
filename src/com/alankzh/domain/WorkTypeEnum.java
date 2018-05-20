package com.alankzh.domain;

public enum WorkTypeEnum{
    unClocking(0),//0、未打卡
    normal(1),//1、正常上班
    beLate(2),//2、迟到
    absent(3),//3、旷班
    sickLeave(4),//4、病假
    personalLeave(5),//事假
    statutoryHoliday(6),//法定假日
    unDefine(10);//未定义的
    
    private int type;
    private WorkTypeEnum(int type) {
        this.type=type;
    }
    public int getType() {
        return type;
    }
    public static WorkTypeEnum valueOf(int value) {    
        switch (value) {  
        case 0:  
            return unClocking;  
        case 1:  
            return normal;
        case 2:  
            return beLate;
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