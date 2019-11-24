package com.njuse.schedule.controller;

import com.njuse.schedule.constant.SimpleResponse;
import com.njuse.schedule.form.ActivityForm;
import com.njuse.schedule.service.ActivityService;
import com.njuse.schedule.vo.ActivityVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity/")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @ApiOperation(value = "获取已发布活动", response = ActivityVO.class,notes = "返回SimpleResponse对象，SimpleResponse对象Data为ActivityVO")
    @GetMapping("public")
    public SimpleResponse getPublicView(){
        return SimpleResponse.ok(activityService.searchAllPublicActivity());
    }

    @ApiOperation(value = "获取用户的所有活动", response = ActivityVO.class,notes = "返回SimpleResponse对象，SimpleResponse对象Data为ActivityVO")
    @GetMapping("private/{userId}")
    public SimpleResponse getPrivateView(@PathVariable int userId){
        return SimpleResponse.ok(activityService.searchActivityByUserId(userId));
    }

    @ApiOperation(value = "获取用户的活动细节", response = ActivityVO.class,notes = "返回SimpleResponse对象，SimpleResponse对象Data为ActivityVO")
    @GetMapping("private/{userId}/{id}")
    public SimpleResponse getPrivateViewInDetail(@PathVariable int userId,@PathVariable int id){
        return SimpleResponse.ok(activityService.searchOneActivityByIdAndUserId(id,userId));
    }

    @ApiOperation(value = "获取活动", response = ActivityVO.class,notes = "返回SimpleResponse对象，SimpleResponse对象Data为ActivityVO")
    @GetMapping("{id}")
    public SimpleResponse searchAct(@PathVariable int id){
        return SimpleResponse.ok(activityService.searchActivityByActivityId(id));
    }

    @ApiOperation(value = "添加新活动", response = ActivityVO.class,notes = "返回SimpleResponse对象，如果添加成功，SimpleResponse对象Data为ActivityVO")
    @PostMapping("addNew")
    public SimpleResponse addNewAct(@RequestBody ActivityForm addActivityForm){
        try{
            return SimpleResponse.ok(activityService.addNewActivity(addActivityForm));
        }catch (DuplicateKeyException e){
            return SimpleResponse.error("添加活动失败");
        }
    }

    @ApiOperation(value = "添加已有的、已发布的活动", response = ActivityVO.class,notes = "返回SimpleResponse对象，如果添加成功，SimpleResponse对象Data为ActivityVO")
    @PostMapping("addOld/{userId}/{activityId}")
    public SimpleResponse addOldAct(@PathVariable int userId,@PathVariable int activityId){
        try{
            activityService.addOldActivity(userId,activityId);
            return SimpleResponse.ok("添加活动成功");
        }catch (DuplicateKeyException e){
            return SimpleResponse.error("添加活动失败");
        }
    }

    @ApiOperation(value = "修改活动", response = ActivityVO.class,notes = "返回SimpleResponse对象，如果修改成功，SimpleResponse对象Data为字符串 修改活动成功")
    @PostMapping("update")
    public SimpleResponse updateAct(@RequestBody ActivityForm updateActivityForm){
        String s = activityService.updateActivity(updateActivityForm);
        if(s!=null){
            return SimpleResponse.error(s);
        }else {
            return SimpleResponse.ok("修改活动成功");
        }
    }


}
