package com.example.kintai.dto;

public class LoginRequest {
    private String userId;
    private String password;

    // デフォルトコンストラクタ（Springで必要）
    public LoginRequest() {}

    // 全フィールドコンストラクタ
    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    // getter / setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}