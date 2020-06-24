package com.hzy.com.hzy.bean;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class Teacher {

    @NotNull( groups = {Update.class}, message = "id cannot be null")
    private Integer id;
    @NotNull(message = "teacher's name cannot be null")
    private String name;
    @Valid
    @NotNull
    private List<UserInfo> userInfoList;

}
