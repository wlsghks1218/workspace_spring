package org.joonzis.service;

import org.joonzis.domain.AuthVO;
import org.joonzis.domain.MemberVO;
import org.joonzis.mapper.AuthMapper;
import org.joonzis.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private AuthMapper aMapper;
	
	@Transactional
	@Override
	public int signIn(MemberVO vo) {
		
		int result = mapper.signIn(vo);
		
		if(vo.getAuthList() != null && vo.getAuthList().size() > 0) {
			vo.getAuthList().forEach(auth -> {
				log.info("이게 authVO가 담고 있는 것들이야" + auth);
				log.info("auth.getUserId() = " +auth.getUserId());
				log.info("auth.getAuth() = " +auth.getAuth());
				aMapper.authInsert(auth);
			});
		}
		
//		AuthVO aVo = new AuthVO();
//		aVo.setUserId(vo.getUserId());
//		aVo.setAuth("ROLE_USER");
//		aMapper.authInsert(aVo);
		return result;
	}
}
