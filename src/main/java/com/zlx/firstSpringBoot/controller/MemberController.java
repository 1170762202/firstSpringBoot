package com.zlx.firstSpringBoot.controller;

import com.zlx.firstSpringBoot.config.HostConfig;
import com.zlx.firstSpringBoot.constant.ResponseCode;
import com.zlx.firstSpringBoot.constant.ReturnUtil;
import com.zlx.firstSpringBoot.constant.SaveBean;
import com.zlx.firstSpringBoot.domain.Member;
import com.zlx.firstSpringBoot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    private HostConfig hostConfig;

    @GetMapping("/save")
    public Map save(String name) {
        if (name == null) {
            return ReturnUtil.returnValue(ResponseCode.failed);
        }
        memberService.save(new Member(name));
        return ReturnUtil.returnValue(ResponseCode.success);
    }


    @RequestMapping("/")
    public void test() {
        System.out.println(hostConfig.getHost() + " " + hostConfig.getPort());
    }

}
