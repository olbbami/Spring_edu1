package org.hdcd.controller.member;

import javax.inject.Inject;

import org.hdcd.controller.member.service.IMemberService;
import org.hdcd.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/crud/member")
public class CrudMemberController {
	private static Logger logger = LoggerFactory.getLogger(CrudMemberController.class);
	
	@Inject
	private IMemberService service;
	
	// 회원 등록 페이지    
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String crudMemberRegisterForm(MemberVO member, Model model) {
		model.addAttribute("member",member);
		return "crud/member/register";
	}	
	
	// 회원 등록 기능 
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String crudMemberRegister(MemberVO member, Model model) throws Exception {
		service.register(member);
		model.addAttribute("msg", "등록이 완료되었습니다."); 
		return "crud/member/success";
	}
}
