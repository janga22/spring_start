package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {
	
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	public void 회원가입() {
		//given
		Member member  = new Member();
		member.setName("hello");
		//when
		Long saveId = memberService.join(member);
		//then
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
		
	}
	
	@Test
	public void 중복회원예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		//when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
//		try {
//			memberService.join(member2);
//			fail();
//		}catch(IllegalStateException e){
//			Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
		
		//then
		
	}
	@Test
	public void 모든회원찾기() {

	}
	
	@Test
	public void 회원찾기() {

		
	}
	
	
}
