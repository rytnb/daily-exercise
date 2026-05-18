package com.example.dailyexerciseauth.entity;

import lombok.Data;

@Data
public class Administrator extends User {
    private String adminId;       // 管理员编号，如ADM20231127001
    private String adminName;     // 管理员姓名
}