package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberService {

	private final MemberRepository memberReopsitory = new MemoryMemberRepository() ;
	
	public Long join(Member member) {
		//같은 이름이 있는 중복 회원 X
		validateDuplicateMember(member);
		memberReopsitory.sava(member);
		return member.getId();
	}
	private void validateDuplicateMember(Member member) {
		
		Optional<Member> result = memberReopsitory.findByName(member.getName());
		result.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
		
	}
	//전체 회원조회
	public List<Member> findMembers(){
		return	memberReopsitory.findAll();	
	}
	public Optional<Member> findOne(Long memberId){
		return memberReopsitory.findById(memberId);
	}
}
