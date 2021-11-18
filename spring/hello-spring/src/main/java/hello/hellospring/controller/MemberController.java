package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
	// @Controller 어노테이션 작성시 스프링에서 컨테이너를 만듦
	// 스프링 컨테이너에 다 등록을 하게 되고 스프링 컨테이너에서 받아와야함
	// @Service, @Repository, @Controller
	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 회원가입 폼화면 이동
	@GetMapping("/members/new")
	public String createForm(){
		return "members/createMemberForm";
	}

	// PostMapping : 데이터를 form에 넣어서 전달할 때
	// GetMapping : 보통 조회용
	@PostMapping("/members/new")
	public String create(MemberForm form){
		Member member = new Member();
		member.setName(form.getName());

		System.out.println("member = " + member.getName());

		memberService.join(member);

		return "redirect:/";
	}

	// 회원 조회
	@GetMapping("/members")
	public String list(Model model){
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}

}
