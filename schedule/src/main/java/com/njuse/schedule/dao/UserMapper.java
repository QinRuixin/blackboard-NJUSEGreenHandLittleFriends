package com.njuse.schedule.dao;

import com.njuse.schedule.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int insert(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    UserEntity selectByEmail(String email);

    int deleteByPrimaryKey(Integer id);
    int insertSelective(UserEntity record);
    int updateByPrimaryKeySelective(UserEntity record);
    int updateByPrimaryKeyWithBLOBs(UserEntity record);
    int updateByPrimaryKey(UserEntity record);
}
