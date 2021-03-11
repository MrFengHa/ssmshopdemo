package com.home.service.impl;

import com.home.mapper.AuthMapper;
import com.home.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2021/3/11 17:10
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;
}
