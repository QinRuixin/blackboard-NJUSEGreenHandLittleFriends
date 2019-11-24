package com.njuse.schedule.service.impl;

import com.njuse.schedule.constant.ResponseCode;
import com.njuse.schedule.constant.ServerException;
import com.njuse.schedule.constant.SimpleResponse;
import com.njuse.schedule.dao.ActivityMapper;
import com.njuse.schedule.dao.ClassMapper;
import com.njuse.schedule.entity.ClassEntity;
import com.njuse.schedule.entity.UserEntity;
import com.njuse.schedule.form.ActivityForm;
import com.njuse.schedule.form.ClassForm;
import com.njuse.schedule.service.ClassService;
import com.njuse.schedule.vo.ClassVO;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassMapper classMapper;
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public List<ClassVO> searchAllClass(int userId) {
        return fromEntity2VO(classMapper.selectAllClass(userId));
    }

    private List<ClassVO> fromEntity2VO(List<ClassEntity> classEntities) {
        List<ClassVO> classVOS = new ArrayList<>();
        for(ClassEntity classEntity:classEntities){
            classVOS.add(new ClassVO(classEntity));
        }
        return classVOS;
    }

    @Override
    public void addNewClass(ClassForm classForm) {
        classMapper.insertOneClass(classForm);
        int classId = classMapper.selectByCode(classForm.getCode()).getId();
        classMapper.insertOneUserClassRecord(classForm.getUserId(),classId);
    }

    @Override
    public void addOldClass(int userId, int code) {
        ClassEntity classEntity = classMapper.selectByCode(code);
        if(classEntity==null){
            throw new ServerException(ResponseCode.Error,"班级尚不存在");
        }
        if(classMapper.selectClassByUserIdAndClassId(userId,classEntity.getId())!=null){
            throw new ServerException(ResponseCode.Error,"已添加过进该班级");
        }
        classMapper.insertOneUserClassRecord(userId,classEntity.getId());
    }

    @Override
    public void addActToClass(ActivityForm activityForm, int classId) {
        activityMapper.insertOneActivity(activityForm);
        List<UserEntity> userEntities = classMapper.selectUserByClassId(classId);
        for (UserEntity userEntity:userEntities){
            if(activityForm.getUserId()==userEntity.getId()){
                continue;
            }
            activityMapper.insertOneUserActivityRecord(userEntity.getId(),activityForm.getId());
        }
    }

    @Override
    public ClassVO searchClassByCode(int code) {
        return new ClassVO(classMapper.selectByCode(code));
    }
}
