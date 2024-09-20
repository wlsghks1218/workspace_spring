package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;
import org.joonzis.mapper.BoardAttachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardAttachServiceImpl implements BoardAttachService{

	@Autowired
	private BoardAttachMapper mapper;
	
	@Override
	public void insert(BoardAttachVO vo) {
		mapper.insert(vo);
	}

	@Override
	public void delete(String uuid) {
		mapper.delete(uuid);
	}

	@Override
	public List<BoardAttachVO> findByBno(int bno) {
		return mapper.findByBno(bno);
	}
	
}
