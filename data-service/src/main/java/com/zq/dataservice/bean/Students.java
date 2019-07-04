package com.zq.dataservice.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试用学生类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Students {

    private int number;

    private String name;

    private int age;

    private String address;

    private String education;

}
