package com.njuse.schedule.service.impl;

import com.njuse.schedule.dao.ActivityMapper;
import com.njuse.schedule.dao.UserMapper;
import com.njuse.schedule.entity.ActivityEntity;
import com.njuse.schedule.entity.UserEntity;
import com.njuse.schedule.form.ActivityForm;
import com.njuse.schedule.service.ActivityService;
import com.njuse.schedule.vo.ActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<ActivityVO> searchAllPublicActivity() {
//        List<ActivityVO> activityVOs= new ArrayList<>();
        List<ActivityEntity> activityEntities = activityMapper.selectAllPublicActivity();
        return fromEntityToVO(activityEntities);
//        for(ActivityEntity activityEntity:activityEntities){
//            ActivityVO activityVO = new ActivityVO(activityEntity);
//            UserEntity user = userMapper.selectByPrimaryKey(activityVO.getUserId());
//            activityVO.setAuthor(user.getName());
//            activityVO.setDepartment(user.getDepartment());
//            activityVOs.add(activityVO);
//        }
//        return activityVOs;
    }

    private List<ActivityVO> fromEntityToVO(List<ActivityEntity> activityEntities) {
        if (activityEntities == null) {
            return null;
        }
        List<ActivityVO> activityVOs = new ArrayList<>();

        for (ActivityEntity activityEntity : activityEntities) {
            activityVOs.add(fromEntityToVO(activityEntity));
        }
        return activityVOs;
    }

    private ActivityVO fromEntityToVO(ActivityEntity activityEntity) {
        if (activityEntity == null) {
            return null;
        }
        ActivityVO activityVO = new ActivityVO(activityEntity);

        UserEntity user = userMapper.selectByPrimaryKey(activityVO.getUserId());
        if (user != null) {
            activityVO.setAuthor(user.getName());
            activityVO.setDepartment(user.getDepartment());
        }
        return activityVO;
    }

    @Override
    public List<ActivityVO> searchActivityByUserId(int userId) {
        List<ActivityEntity> activityEntities = activityMapper.selectActivityByUserId(userId);
        return fromEntityToVO(activityEntities);
    }

    @Override
    public ActivityVO searchOneActivityByIdAndUserId(int id, int userId) {
        ActivityEntity activityEntity = activityMapper.selectActivityById(id);
        ActivityVO activityVO = fromEntityToVO(activityEntity);
        if (activityEntity.getUserId() == userId) {
            activityVO.setIsAuthor(1);
        } else {
            activityVO.setIsAuthor(0);
        }
        return activityVO;
    }

    @Override
    public ActivityVO searchActivityByActivityId(int activityId) {
        return fromEntityToVO(activityMapper.selectActivityById(activityId));
    }

    @Override
    public ActivityVO addNewActivity(ActivityForm activity) {
        activityMapper.insertOneActivity(activity);
        int actId = activity.getId();
        activityMapper.insertOneUserActivityRecord(activity.getUserId(), actId);
        return fromEntityToVO(activityMapper.selectActivityById(actId));
    }

    @Override
    public void addOldActivity(Integer userId, Integer activityId) {
        activityMapper.insertOneUserActivityRecord(userId, activityId);
    }


    @Override
    public void delActivity(Integer activityId) {

    }

    @Override
    public String updateActivity(ActivityForm activity) {
        activityMapper.updateActivity(activity);
        return null;
    }
}
