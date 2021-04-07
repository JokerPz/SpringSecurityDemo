package com.jokerpz.service.impl;

import com.jokerpz.service.MyService;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public class MyServiceImpl implements MyService {
    @Override
    public Boolean hasPermisson(HttpServletRequest request, Authentication authentication) {

        Object obj = authentication.getPrincipal();

        return false;
    }
}
