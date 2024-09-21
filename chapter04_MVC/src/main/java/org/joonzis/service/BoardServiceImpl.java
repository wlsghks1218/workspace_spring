package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;
import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.mapper.BoardAttachMapper;
import org.joonzis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private BoardAttachMapper attachMapper;
	
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
	@Transactional
	public int register(BoardVO vo) {
		log.info("------ register..." + vo);
		
		// 1. tbl_board 게시글 등록
		int result = mapper.insert(vo);
		
		// 2. tbl_attach 등록
		log.info("여기서 부터 첨부파일 입력 입니다.");
		if(vo.getAttachList() != null && vo.getAttachList().size() > 0) {
			vo.getAttachList().forEach(attach -> {
				log.info("이게 attachVO가 담고 있는 것들이야" + attach);
				attach.setBno(vo.getBno());
				attachMapper.insert(attach);
			});
		}
		return result;
	}

	@Override
	public int modify(BoardVO vo) {
		log.info("------ modify..." + vo);
		int result = mapper.update(vo);
		
		if(vo.getAttachList() != null && vo.getAttachList().size() > 0) {
			vo.getAttachList().forEach(attach -> {
				attach.setBno(vo.getBno());
				attachMapper.delete(attach.getUuid());
				attachMapper.insert(attach);
			});
		}
		
		return result;
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

	@Override
	public List<BoardAttachVO> getAttachList(int bno) {
		log.info("getAttachList..." + bno);
		return attachMapper.findByBno(bno);
	}
	
	@Override
	public void deleteAttach(String uuid) {
		log.info("attachFile delete..." + uuid);
		attachMapper.delete(uuid);
	}
}
