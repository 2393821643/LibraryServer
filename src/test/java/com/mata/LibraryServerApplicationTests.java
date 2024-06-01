package com.mata;

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
    @Test
    void contextLoads() {
        Integer i = jwtUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.unVvql5_3DYhvimNYiVO1NpXi2wHzifxihZ5fNKPA1y");
        System.out.println(i);
    }

}
