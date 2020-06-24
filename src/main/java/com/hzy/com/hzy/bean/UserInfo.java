package com.hzy.com.hzy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Data
@AllArgsConstructor
public class UserInfo {
    @NotNull(message = "id cannot be null" ,groups = Update.class)
    private Long id;

    @NotNull(  message = "name cannot be null" , groups = Default.class)
    private String name;

    @NotNull(message = "sex cannot be null" , groups = Default.class)
    private String sex;

    @Max(value = 99L, groups = Default.class)
    private Integer age;


}