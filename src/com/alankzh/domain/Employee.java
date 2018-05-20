package com.alankzh.domain;

import java.time.LocalDate;

import com.alankzh.annotation.NotThreadSafe;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 员工表
 * @author alankzh
 *
 */
@NotThreadSafe
public class Employee {
    private int id;
    private String name;
    private int departmentId;
    private String departmentName;
    
    @JSONField(serialize=false)
    private int age;
    
    @JSONField(serialize=false)
    private String gender;
    
    private String photoUrl;
    private LocalDate birthday;//yyyy-MM-dd 舍去时间部分
    
    @JSONField(serialize=false)
    private String hobby;//爱好
    
    private String phoneNum;
    
    @JSONField(serialize=false)
    private String email;
    
    private String qq;
    private String weChat;//微信
    
    @JSONField(serialize=false)
    private boolean marryed;//是否婚配
    
    @JSONField(serialize=false)
    private boolean hasChildren;//是否有子女
    
    public Employee() {
        name="";
        gender="";
        photoUrl="";
        hobby="";
        phoneNum="";
        email="";
        qq="";
        weChat="";
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
    public int getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getHobby() {
        return hobby;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getWeChat() {
        return weChat;
    }
    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }
    public boolean isMarryed() {
        return marryed;
    }
    public void setMarryed(boolean marryed) {
        this.marryed = marryed;
    }
    public boolean isHasChildren() {
        return hasChildren;
    }
    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
}
