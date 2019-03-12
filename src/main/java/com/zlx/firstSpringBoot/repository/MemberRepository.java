package com.zlx.firstSpringBoot.repository;

import com.zlx.firstSpringBoot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * {@link Member} {@link Repository}
 */
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

}
