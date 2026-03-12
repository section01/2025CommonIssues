package com.example.kintai.dto;

import com.example.kintai.entity.Employee;

public class LoginResponse {
    private boolean success;   // ログイン結果
    private String message;    // 成功／失敗メッセージ
    private Employee user;         // ログイン成功時に取得したユーザ情報（必要な場合）

    // デフォルトコンストラクタ
    public LoginResponse() {}

    // 全フィールドコンストラクタ
    public LoginResponse(boolean success, String message, Employee user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    // getter / setter
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }
}