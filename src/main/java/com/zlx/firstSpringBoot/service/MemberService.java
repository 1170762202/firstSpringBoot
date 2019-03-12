package com.zlx.firstSpringBoot.service;

import com.zlx.firstSpringBoot.domain.Member;
import com.zlx.firstSpringBoot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void save(Member member){
       memberRepository.save(member);
    }
}
