package com.njuse.schedule.vo;

import com.njuse.schedule.entity.UserEntity;
import lombok.Data;

@Data
public class UserVO {
    private Integer id;

    private String name;

    private String email;

    private String department;

    private String logoUrl;

    public UserVO(UserEntity userEntity) {
        if (userEntity != null) {
            this.id = userEntity.getId();
            this.email = userEntity.getEmail();
            this.logoUrl = userEntity.getLogoUrl();
            this.name = userEntity.getName();
            this.department = userEntity.getDepartment();
        }
    }
}
