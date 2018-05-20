package com.alankzh.domain;

import com.alankzh.annotation.NotThreadSafe;

/**
 * 部门关系表，parentId和subId外键约束Department表的id
 * @author alankzh
 *
 */
@NotThreadSafe
public class DepartmentRelation {
    private int id;//对应数据库id
    private int parentId;//父部门id
    private int subId;//子部门id
    private boolean isDefaultSub;//此父部门默认子部门为此 default false,not null
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getParentId() {
        return parentId;
    }
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    public int getSubId() {
        return subId;
    }
    public void setSubId(int subId) {
        this.subId = subId;
    }
    public boolean isDefaultSub() {
        return isDefaultSub;
    }
    public void setDefaultSub(boolean isDefaultSub) {
        this.isDefaultSub = isDefaultSub;
    }
    
}
