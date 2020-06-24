package com.hzy.controller;


import com.hzy.com.hzy.bean.GroupA;
import com.hzy.com.hzy.bean.Teacher;
import com.hzy.com.hzy.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String getUserStr(@RequestBody  @Validated({GroupA.class, Default.class}) UserInfo user, BindingResult bindingResult) {
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

    @PostMapping("/teacher")
    public String getTeacherWithUserInfo(@Valid @RequestBody Teacher teacher, BindingResult br){
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

}