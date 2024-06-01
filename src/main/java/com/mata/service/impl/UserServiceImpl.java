package com.mata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mata.dao.UserDao;
import com.mata.dto.Result;
import com.mata.pojo.User;
import com.mata.service.UserService;
import com.mata.util.JwtUtil;
import com.mata.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private WxUtil wxUtil;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * wx登录
     */
    @Override
    public Result<String> login(String code) {
        String openId = wxUtil.wxLogin(code);
        if (openId == null) {
            return Result.error("登录失败");
        }
        // 数据库查openid是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenId, openId);
        User user = baseMapper.selectOne(wrapper);
        // 不存在添加
        if (user == null) {
            user = User.builder()
                    .openId(openId)
                    .build();
            save(user);
        }
        // 返回token
        String token = jwtUtil.createToken(user.getId());
        return Result.success(token,"登录成功");
    }
}
