package com.mata.controller;

import com.mata.dto.Result;
import com.mata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
    * 用户微信登录
    * @param code 登录code
    */
    @PostMapping("/login")
    public Result<String> login(@RequestParam("code") String code){
        return userService.login(code);
    }
}
