package com.njuse.schedule.service;

import com.njuse.schedule.form.ActivityForm;
import com.njuse.schedule.vo.ActivityVO;

import java.util.List;

public interface ActivityService {

    /**
     * 搜索全部 发布出去的活动，用于活动广场，转化为ActivityVO应添加发布人姓名及其院系
     *
     * @return 包含 所有发布出去的ActivityVO 的列表
     */
    public List<ActivityVO> searchAllPublicActivity();

    /**
     * 根据活动id以及用户id查找活动，用于某个用户在活动广场点具体的活动
     *
     * @param id
     * @param userId
     * @return 活动id的活动对 用户id的用户的ActivityVO
     */
    public ActivityVO searchOneActivityByIdAndUserId(int id, int userId);

    /**
     * 根据用户id查找活动，用于某个用户在schedule界面查看内容
     *
     * @param userId
     * @return 该user的全部schedule
     */
    public List<ActivityVO> searchActivityByUserId(int userId);

    /**
     * 添加新活动，u_a表中同时更新
     * @param activity
     * @return 返回插入数据库的Activity的Id
     */
    public ActivityVO addNewActivity(ActivityForm activity);

    /**
     * 将已存在活动添加到日程中,插入到u_a表中
     * @param userId
     * @param activityId
     * @return
     */
    public void addOldActivity(Integer userId,Integer activityId);

    public ActivityVO searchActivityByActivityId(int activityId);

    public void delActivity(Integer activityId);

    //TODO 检查权限
    public String updateActivity(ActivityForm activity);

//    public ActivityVO checkActivity(Integer activityId);

}
