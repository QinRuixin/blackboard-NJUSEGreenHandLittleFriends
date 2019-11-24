package com.njuse.schedule.service.impl;

import com.njuse.schedule.aop.LoggerManage;
import com.njuse.schedule.constant.ResponseCode;
import com.njuse.schedule.constant.ServerException;
import com.njuse.schedule.dao.UserMapper;
import com.njuse.schedule.entity.UserEntity;
import com.njuse.schedule.form.UserForm;
import com.njuse.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @LoggerManage(description = "创建用户 UserServiceImpl.createUser")
    public UserEntity createUser(UserForm userForm) {
        UserEntity targetUser = userMapper.selectByEmail(userForm.getEmail());
        if (null != targetUser)
            throw new ServerException(ResponseCode.Error, "Repeat of username");

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userForm.getName());
        userEntity.setEmail(userForm.getEmail());
        userEntity.setLogoUrl(userForm.getLogoUrl());
        userEntity.setDepartment(userForm.getDepartment());
        try {
            userEntity.setPassword(userForm.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException(ResponseCode.Error, "Encode error");
        }
        userMapper.insert(userEntity);
        return userEntity;
    }

    @Override
    public void modifyPassword(int id, String oldPassword, String newPassword) {
        UserEntity userEntity = userMapper.selectByPrimaryKey(id);

        if (!userEntity.getPassword().equals(oldPassword)) {
            throw new ServerException(ResponseCode.Error, "Password error");
        }
        userEntity.setPassword(newPassword);

    }

    @Override
    public UserEntity getUser(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
