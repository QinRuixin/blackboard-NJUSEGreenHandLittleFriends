package com.njuse.schedule.vo;

import com.njuse.schedule.entity.ClassEntity;
import lombok.Data;

@Data
public class ClassVO {

    private Integer id;

    private String name;

    private int code;

    private int userId;

    public ClassVO(ClassEntity classEntity){
        if(classEntity!=null){
            this.id = classEntity.getId();
            this.code = classEntity.getCode();
            this.name = classEntity.getName();
            this.userId = classEntity.getUserId();
        }
    }
}
