package com.alankzh.domain;

/**
 * 员工变动表
 * @author alankzh
 *
 */
public class EmployAlteration {
    private int versionNum;
    private int employeeId;
    
    /**
     * 变动方式
     * 1.插入
     * 2.更新
     * 3.删除
     */
    private int alterType;

    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getAlterType() {
        return alterType;
    }

    public void setAlterType(int alterType) {
        this.alterType = alterType;
    }
    
    
}
