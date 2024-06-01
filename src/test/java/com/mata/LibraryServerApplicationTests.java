package com.mata;

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
    }

}
