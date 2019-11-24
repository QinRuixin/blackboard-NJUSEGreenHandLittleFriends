package com.njuse.schedule.controller;

import com.njuse.schedule.constant.ServerException;
import com.njuse.schedule.constant.SimpleResponse;
import com.njuse.schedule.entity.UserEntity;
import com.njuse.schedule.form.PasswordForm;
import com.njuse.schedule.form.UserForm;
import com.njuse.schedule.service.UserService;
import com.njuse.schedule.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "用户注册", response = UserVO.class,notes = "返回SimpleResponse对象，如果成功注册，则SimpleResponse对象Data为UserVO")
    @PostMapping("account")
    public SimpleResponse register(HttpSession session, @RequestBody UserForm userForm){
        UserEntity userEntity = null;
        try {
            userEntity = userService.createUser(userForm);
        } catch (ServerException serverException) {
            return SimpleResponse.error(serverException);
        } catch (Exception e) {
            e.printStackTrace();
            return SimpleResponse.error("Create user error");
        }
        session.setAttribute("user",userEntity);
        return SimpleResponse.ok(new UserVO(userEntity));
    }

    @ApiOperation(value = "修改密码", response = SimpleResponse.class, notes = "需要先登录，修改成功则SimpleResponse对象Data为 字符串Success ")
    @PostMapping("password")
    public SimpleResponse modifyPassword(HttpSession session, @RequestBody PasswordForm passwordForm){

        Object object = session.getAttribute("user");
        if (null == object){
            return SimpleResponse.error("Please login");
        }
        UserEntity userEntity = (UserEntity) object;
        try {
            userService.modifyPassword(userEntity.getId(),passwordForm.getOldPassword(),passwordForm.getNewPassword());
            session.setAttribute("user",userEntity);
        }catch (ServerException serverException){
            return SimpleResponse.error(serverException.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return SimpleResponse.error("Modify the password error");
        }

        return SimpleResponse.ok("Success");
    }

}
