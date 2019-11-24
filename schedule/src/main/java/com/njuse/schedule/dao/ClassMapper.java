package com.njuse.schedule.dao;

import com.njuse.schedule.entity.ClassEntity;
import com.njuse.schedule.entity.U_cEntity;
import com.njuse.schedule.entity.UserEntity;
import com.njuse.schedule.form.ClassForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassMapper {

    /**
     * 创建一个班级
     *
     * @param classForm
     * @return
     */
    int insertOneClass(ClassForm classForm);

    /**
     * 根据id查找班级
     *
     * @param id
     * @return
     */
    ClassEntity selectOneClass(int id);

    /**
     * 根据code查找班级
     *
     * @param code
     * @return
     */
    ClassEntity selectByCode(int code);

    /**
     * 根据userId查找该用户已创建的班级
     *
     * @param userId
     * @return
     */
    List<ClassEntity> selectAllClass(int userId);

    /**
     * 将userId与classId 插入u_c关系表
     *
     * @param userId
     * @param classId
     * @return
     */
    int insertOneUserClassRecord(Integer userId,Integer classId);

    /**
     * 根据classId查找属于该班级的所有用户
     * @param classId
     * @return
     */
    List<UserEntity> selectUserByClassId(int classId);

    U_cEntity selectClassByUserIdAndClassId(int userId, int classId);

}
