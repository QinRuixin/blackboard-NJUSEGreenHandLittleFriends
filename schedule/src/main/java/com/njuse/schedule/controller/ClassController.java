package com.njuse.schedule.controller;

import com.njuse.schedule.constant.ServerException;
import com.njuse.schedule.constant.SimpleResponse;
import com.njuse.schedule.form.ActivityForm;
import com.njuse.schedule.form.ClassForm;
import com.njuse.schedule.service.ClassService;
import com.njuse.schedule.vo.ClassVO;
import com.njuse.schedule.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService classService;

    @ApiOperation(value = "创建班级", response = UserVO.class,notes = "返回SimpleResponse对象，如果创建成功，SimpleResponse对象Data为\"Success\"")
    @PostMapping("/add")
    public SimpleResponse addNewClass(@RequestBody ClassForm classForm){
        try{
            classService.addNewClass(classForm);
        }catch (Exception e){
            return SimpleResponse.error("code重复");
        }
        return SimpleResponse.ok("Add success");
    }

    @ApiOperation(value = "获取班级", response = UserVO.class,notes = "返回SimpleResponse对象，如果添加成功，SimpleResponse对象Data为ClassVO")
    @GetMapping("/{userId}")
    public SimpleResponse getAllClass(@PathVariable int userId){
        return SimpleResponse.ok(classService.searchAllClass(userId));
    }

    @ApiOperation(value = "加入班级", response = UserVO.class,notes = "返回SimpleResponse对象，如果加入成功，SimpleResponse对象Data为 Success ")
    @GetMapping("/{userId}/{code}")
    public SimpleResponse enterClass(@PathVariable int userId,@PathVariable int code){
        try {
            classService.addOldClass(userId, code);
        }catch (ServerException e){
            return SimpleResponse.error(e.getMessage());
        }
        return SimpleResponse.ok("Enter success");
    }

    @ApiOperation(value = "添加班级活动", response = UserVO.class,notes = "返回SimpleResponse对象，如果添加成功，SimpleResponse对象Data为 Success ")
    @PostMapping("/{classId}/act")
    public SimpleResponse addClassAct(@PathVariable int classId, @RequestBody ActivityForm activityForm){
        classService.addActToClass(activityForm,classId);
        return SimpleResponse.ok("Success");
    }


    @ApiOperation(value = "添加或加入班级", response = UserVO.class,notes = "返回SimpleResponse对象，如果添加或加入成功，SimpleResponse对象Data为 Success ")
    @PostMapping("/addOrEnter")
    public SimpleResponse addOrEnterClass(@RequestBody ClassForm classForm){
        if(classService.searchClassByCode(classForm.getCode())==null||classService.searchClassByCode(classForm.getCode()).getId()==null){
            return addNewClass(classForm);
//            SimpleResponse.ok("add successfully");
        }else {
            return enterClass(classForm.getUserId(),classForm.getCode());
//            SimpleResponse.ok("enter successfully");
        }

    }

}
