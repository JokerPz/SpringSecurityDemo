package com.jokerpz.security.service.impl;


import com.jokerpz.SpringSecurityApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest(classes = SpringSecurityApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailsServiceImplTest {

    @Test
    public void test() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("lisi");
        System.out.println(result);

        System.out.println("-----------------------");

        boolean ret = encoder.matches("pwd", result);
        System.out.println(ret);
    }
}