package com.example.kintai.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kintai.dto.LoginRequest;
import com.example.kintai.dto.LoginResponse;
import com.example.kintai.entity.Employee;
import com.example.kintai.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class LoginController {

//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginRequest request) {
//        // ここでDBからユーザを検索（今回はサンプルとして固定値で実装）
//        User user = findUserByUserId(request.getUserId());
//
//        // ユーザが存在しない or パスワード不一致
//        if (user == null || !user.getPassword().equals(request.getPassword())) {
//            return new LoginResponse(false, "ユーザIDまたはパスワードが間違っています", null);
//        }
//
//        // ログイン成功
//        return new LoginResponse(true, "ログイン成功", user);
//    }
//
//    // サンプル用：固定値でユーザを返す簡易メソッド
//    private User findUserByUserId(String userId) {
//        // 実際はリポジトリを使ってDB検索する
//        if ("test01".equals(userId)) {
//            return new User("test", "password123", "チーフ", "上坂 祐司", "セクション1");
//        }
//        return null;
//    }


	// パスワードを固定値として行うログイン認証
	@Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        // ① EMP_ID で検索（PK）
        Optional<Employee> empOpt =
                employeeRepository.findById(request.getUserId());

        if (empOpt.isEmpty()) {
            return new LoginResponse(false,
                    "ユーザIDが存在しません",
                    null);
        }

        // ② パスワード固定値チェック
        final String FIXED_PASSWORD = "password";

        if (!FIXED_PASSWORD.equals(request.getPassword())) {
            return new LoginResponse(false,
                    "パスワードが間違っています",
                    null);
        }

        // ③ 成功
        return new LoginResponse(true,
                "ログイン成功",
                empOpt.get());
    }
}