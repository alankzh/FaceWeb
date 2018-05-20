package com.alankzh.domain;

import com.alankzh.annotation.NotThreadSafe;

/**
 * 部门表
 * @author alankzh
 *
 */
@NotThreadSafe
public class Department {
    private int id;//部门id，对应数据库id
    private String name;//部门名，对应数据库名
    
    public Department() {
        name="";
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
