package com.njuse.schedule.dao;

import com.njuse.schedule.entity.ActivityEntity;
import com.njuse.schedule.entity.U_cEntity;
import com.njuse.schedule.form.ActivityForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityMapper {

    /**
     * 查找所有 已发布 的活动
     *
     * @return 已发布出去的ActivityEntity的列表
     */
    public List<ActivityEntity> selectAllPublicActivity();

    /**
     * 查找某个用户schedule界面所有的活动
     *
     * @param userId
     * @return 某个用户的ActivityEntity的列表
     */
    public List<ActivityEntity> selectActivityByUserId(Integer userId);

    /**
     * 根据id查找活动
     *
     * @param id
     * @return
     */
    public ActivityEntity selectActivityById(Integer id);


    /**
     * 插入一条活动记录
     *
     * @param activityForm
     * @return 插入后的活动记录值
     */
    public int insertOneActivity(ActivityForm activityForm);

    /**
     * 将userId与activityId 插入u_a关系表
     *
     * @param userId
     * @param activityId
     * @return
     */
    public int insertOneUserActivityRecord(Integer userId,Integer activityId);

    /**
     * 更新一条活动记录
     *
     * @param activityForm
     * @return
     */
    public int updateActivity(ActivityForm activityForm);


}
