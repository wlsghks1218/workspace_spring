package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		log.info("------ getList...");
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotalRecordCount() {
		return mapper.getTotalRecordCount();
	}

	@Override
	public int register(BoardVO vo) {
		log.info("------ register..." + vo);
		return mapper.insert(vo);
	}

	@Override
	public int modify(BoardVO vo) {
		log.info("------ modify..." + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(int bno) {
		log.info("------ remove..." + bno);
		return mapper.delete(bno);
	}

	@Override
	public BoardVO get(int bno) {
		log.info("------ get..." + bno);
		return mapper.read(bno);
	}

	
}
