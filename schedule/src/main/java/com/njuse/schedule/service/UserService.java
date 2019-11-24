package com.njuse.schedule.service;

import com.njuse.schedule.entity.UserEntity;
import com.njuse.schedule.form.UserForm;

public interface UserService {

    public UserEntity createUser(UserForm userForm);

    public void modifyPassword(int id,String oldPassword, String newPassword);

    public UserEntity getUser(int id);

}
