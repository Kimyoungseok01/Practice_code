package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiecImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiecImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //TEST용용
   public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
