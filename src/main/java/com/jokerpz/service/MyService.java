package com.jokerpz.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyService {

    Boolean hasPermisson(HttpServletRequest request, Authentication authentication);
}
