package com.gangwondog.core.test.web;

import com.gangwondog.core.test.repository.MemberRepository;
import com.gangwondog.core.test.vo.MemberEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MemberController {

  private final MemberRepository memberRepository;

//  @GetMapping("member")
//  public List<MemberEntity> findAllMember() {
//    return memberRepository.findAll();
//  }

  @GetMapping("member")
  public ResponseEntity<List<MemberEntity>> findAllMember() {
    return ResponseEntity.ok(memberRepository.findAll());
  }

  @PostMapping("member")
  public MemberEntity insertMember(@RequestBody MemberEntity memberEntity) {
    final MemberEntity member = MemberEntity.builder()
        .username(memberEntity.getUsername())
        .name(memberEntity.getName())
        .address(memberEntity.getAddress())
        .test(memberEntity.getTest())
        .build();
    return memberRepository.save(member);
  }

}
