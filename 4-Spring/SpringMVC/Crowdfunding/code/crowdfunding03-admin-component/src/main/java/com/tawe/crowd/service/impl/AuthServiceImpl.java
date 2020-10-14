package com.tawe.crowd.service.impl;

import com.tawe.crowd.dao.AuthMapper;
import com.tawe.crowd.entity.Auth;
import com.tawe.crowd.entity.AuthExample;
import com.tawe.crowd.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAllAuths() {
        return authMapper.selectByExample(new AuthExample());
    }
}
