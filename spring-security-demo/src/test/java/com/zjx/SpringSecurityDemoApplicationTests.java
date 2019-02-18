package com.zjx;

import com.zjx.security.CustomUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringSecurityDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private CustomUserService customUserService;

    @Test
    public void loadUserByUsernameTest(){
        String username = "zjx";
        UserDetails userDetails = customUserService.loadUserByUsername(username);
        System.out.println(userDetails);
    }

}

