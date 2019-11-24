package com.njuse.schedule.service;

import com.njuse.schedule.form.ActivityForm;
import com.njuse.schedule.form.ClassForm;
import com.njuse.schedule.vo.ClassVO;
import java.util.List;

public interface ClassService {

    /**
     * 搜索所有该用户创建的班级
     * @param userId
     * @return
     */
    public List<ClassVO> searchAllClass(int userId);

    /**
     * 创建新班级
     *
     * @param classForm
     */
    public void addNewClass(ClassForm classForm);

    /**
     * 将用户添加进班级
     *
     * @param userId
     * @param code
     */
    public void addOldClass(int userId,int code);

    /**
     * 添加活动到该班级的每个成员
     *
     * @param activityForm
     * @param classId
     */
    public void addActToClass(ActivityForm activityForm,int classId);


    public ClassVO searchClassByCode(int code);
}
