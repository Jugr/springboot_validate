package com.hzy.com.hzy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UserInfo {

    @NotNull(  message = "name cannot be null")
    private String name;

    @NotNull(message = "sex cannot be null")
    private String sex;

    @Max(value = 99L)
    private Integer age;


}