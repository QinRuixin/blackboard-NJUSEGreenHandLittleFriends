package com.njuse.schedule.service.impl;

import com.njuse.schedule.constant.ResponseCode;
import com.njuse.schedule.constant.ServerException;
import com.njuse.schedule.dao.UserMapper;
import com.njuse.schedule.entity.UserEntity;
import com.njuse.schedule.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserEntity login(String email, String password) {
        UserEntity userEntity = userMapper.selectByEmail(email);
        if (!userEntity.getPassword().equals(password)) {
            throw new ServerException(ResponseCode.Error, "Password error");
        }
        return userEntity;
    }

}
