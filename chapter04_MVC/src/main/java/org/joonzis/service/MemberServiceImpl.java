package org.joonzis.service;

import org.joonzis.domain.AuthVO;
import org.joonzis.domain.MemberVO;
import org.joonzis.mapper.AuthMapper;
import org.joonzis.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private AuthMapper aMapper;
	
	@Transactional
	@Override
	public int signIn(MemberVO vo) {
		AuthVO aVo = new AuthVO();
		aVo.setUserId(vo.getUserId());
		aVo.setAuth("ROLE_USER");
		int result = mapper.signIn(vo);
		aMapper.authInsert(aVo);
		return result;
	}
}
