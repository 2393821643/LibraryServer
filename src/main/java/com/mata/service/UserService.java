package com.mata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mata.dto.Result;
import com.mata.pojo.User;

public interface UserService extends IService<User> {
    Result<String> login(String code);
}
