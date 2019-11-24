package com.njuse.schedule.service;

import com.njuse.schedule.entity.UserEntity;

public interface SessionService {

    public UserEntity login(String email, String password);

}
