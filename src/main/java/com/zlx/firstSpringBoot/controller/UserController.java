package com.zlx.firstSpringBoot.controller;

import com.zlx.firstSpringBoot.config.HostConfig;
import com.zlx.firstSpringBoot.constant.ResponseCode;
import com.zlx.firstSpringBoot.constant.ReturnUtil;
import com.zlx.firstSpringBoot.domain.User;
import com.zlx.firstSpringBoot.constant.response.UserResp;
import com.zlx.firstSpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private HostConfig hostConfig;


    @PostMapping("/save")
    public Map save(String name) {
        if (name == null) {
            return ReturnUtil.returnValue(ResponseCode.failed);
        }
        return ReturnUtil.returnValue(ResponseCode.success);
    }


    @RequestMapping("/")
    public void test() {
        System.out.println(hostConfig.getHost() + " " + hostConfig.getPort());
    }

    @RequestMapping("/selectAll")
    public Object select() {
        return ReturnUtil.returnValue(ResponseCode.success, new UserResp(userService.selectAll()));
    }

    @RequestMapping("/insert")
    public Object insert(User user) {
        userService.insert(user);
        return ReturnUtil.returnValue(ResponseCode.success);
    }

    @RequestMapping("/deleteById")
    public Object deleteById(Long id) {
        userService.deleteUser(id);
        return ReturnUtil.returnValue(ResponseCode.success);
    }

    @RequestMapping("/updateUserName")
    public Object updateUserName(Long id, String name) {
        int a = 1 / 0;
        userService.updateUserName(id, name);
        return ReturnUtil.returnValue(ResponseCode.success);
    }

}
