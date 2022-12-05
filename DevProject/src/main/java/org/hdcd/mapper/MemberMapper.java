package org.hdcd.mapper;

import org.hdcd.vo.MemberAuth;
import org.hdcd.vo.MemberVO;

public interface MemberMapper {
	public void create(MemberVO member) throws Exception;
	public void createAuth(MemberAuth memberAuth) throws Exception;
}
