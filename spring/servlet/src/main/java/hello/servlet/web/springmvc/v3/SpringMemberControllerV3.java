package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("springmvc/v3/members")
public class SpringMemberControllerV3 {
	private MemberRepository memberRepository = MemberRepository.getInstance();

	@GetMapping("/new-form")// get 방식만 허용
	public String newForm() {
		return "new-form";
	}
	// v2와 비교했을 때 단순히 String만 반환

	@PostMapping("/save")
	public String save(
			@RequestParam("username") String username,
			@RequestParam("age") int age,
			Model model) {

		Member member = new Member(username, age);
		memberRepository.save(member);

		model.addAttribute("member", member);
		return "save-result";
	}

	@GetMapping
	public String members(Model model) {

		List<Member> members = memberRepository.findAll();

		model.addAttribute("members", members);
		return "members";
	}
}
