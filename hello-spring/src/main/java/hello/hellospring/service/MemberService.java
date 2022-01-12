package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberService {

	private final MemberRepository memberReopsitory = new MemoryMemberRepository() ;
	
	public Long join(Member member) {
		//���� �̸��� �ִ� �ߺ� ȸ�� X
		validateDuplicateMember(member);
		memberReopsitory.sava(member);
		return member.getId();
	}
	private void validateDuplicateMember(Member member) {
		
		Optional<Member> result = memberReopsitory.findByName(member.getName());
		result.ifPresent(m -> {
			throw new IllegalStateException("�̹� �����ϴ� ȸ���Դϴ�.");
		});
		
	}
	//��ü ȸ����ȸ
	public List<Member> findMembers(){
		return	memberReopsitory.findAll();	
	}
	public Optional<Member> findOne(Long memberId){
		return memberReopsitory.findById(memberId);
	}
}
