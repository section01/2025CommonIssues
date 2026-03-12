package com.example.kintai.entity;

public class User {
    private String userId;      // ユーザID（ログイン用）
    private String name;        // 名前
    private String department;  // 所属チーム
    private String role;        // 役職
    private String password;    // パスワード（DB格納用）

    // デフォルトコンストラクタ
    public User() {}

    // 全フィールドコンストラクタ
    public User(String userId, String name, String department, String role, String password) {
        this.userId = userId;
        this.name = name;
        this.department = department;
        this.role = role;
        this.password = password;
    }

    // getter / setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}