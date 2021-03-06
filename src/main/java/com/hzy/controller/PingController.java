package com.hzy.controller;


import com.hzy.com.hzy.bean.Update;
import com.hzy.com.hzy.bean.Teacher;
import com.hzy.com.hzy.bean.UserInfo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@RestController
@Validated
public class PingController {

    @GetMapping("/getUser")
    public String getUserStr(@NotNull(message = "name 不能为空") String name,
                             @Max(value = 99, message = "不能大于99岁") Integer age) {
        return "name: " + name + " ,age:" + age;
    }

    @PostMapping("/getUser")
    public String getUserStr(@RequestBody  @Validated({Update.class, Default.class}) UserInfo user, BindingResult bindingResult) {
//        其中Default为javax.validation.groups中的类，表示参数类中其他没有分组的参数，如果没有，/getUser接口的参数校验就只会有标记了GroupA的参数校验生效。

        validData(bindingResult);

        return user.toString();
    }

    private void validData(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                sb.append(error.getDefaultMessage());
            }
            throw new ValidationException(sb.toString());
        }
    }



    @PostMapping("/userList")
    public String getUserInfoList(@Valid @RequestBody List<UserInfo> userInfos , BindingResult br){
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (ObjectError error : br.getAllErrors()) {
                sb.append(error.getDefaultMessage());
            }

        }else {
            userInfos.forEach(u -> sb.append(u.toString()));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    @PostMapping("/teacherInsert")//user id 可以为空
    public String teacherInsert(@Validated(value = {Default.class}) @RequestBody Teacher teacher, BindingResult br){
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (ObjectError error : br.getAllErrors()) {
                sb.append(error.getDefaultMessage());
            }

        }else {
            sb.append(teacher.getUserInfoList().toString());
        }

        return sb.toString();

    }


    @PostMapping("/teacherUpdate")//user id不能为空
    // {Update.class, Default.class}有Update.class就要带上Default.class，否则会覆盖掉Default.class*********************
    public String teacherUpdate(@Validated(value = {Update.class, Default.class}) @RequestBody Teacher teacher, BindingResult br){
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (ObjectError error : br.getAllErrors()) {
                sb.append(error.getDefaultMessage());
            }

        }else {
            sb.append(teacher.getUserInfoList().toString());
        }

        return sb.toString();

    }
}