package org.hdcd.controller.member.service;

import javax.inject.Inject;

import org.hdcd.mapper.MemberMapper;
import org.hdcd.vo.MemberAuth;
import org.hdcd.vo.MemberVO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMemberService {
	
	@Inject
	private MemberMapper mapper;
	
	@Override
	public void register(MemberVO member) throws Exception {
		mapper.create(member);
		
		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setUserNo(member.getUserNo());
		memberAuth.setAuth("ROLE_USER"); // 기본 사용자 등급 
		mapper.createAuth(memberAuth);
	}
	

}
