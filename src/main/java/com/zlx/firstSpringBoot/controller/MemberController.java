package com.zlx.firstSpringBoot.controller;

import com.zlx.firstSpringBoot.domain.Member;
import com.zlx.firstSpringBoot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/save")
    public String save(String name) {
        if (name == null){
            return "name is null";
        }
        memberService.save(new Member(name));
        return "success";
    }

}
