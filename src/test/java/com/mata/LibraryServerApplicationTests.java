package com.mata;

import com.mata.pojo.User;
import com.mata.service.UserService;
import com.mata.util.JwtUtil;
import com.mata.util.WxUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryServerApplicationTests {

    @Autowired
    private WxUtil wxLogin;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        User user = new User(null, "testopen");
        userService.save(user);
        System.out.println(user);
    }

}
