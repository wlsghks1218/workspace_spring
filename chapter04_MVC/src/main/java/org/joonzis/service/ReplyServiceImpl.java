package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.ReplyVO;
import org.joonzis.mapper.BoardMapper;
import org.joonzis.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	@Transactional
	public int replyInsert(ReplyVO vo) {
		log.info("---replyInsert"+vo);
		
		// replycnt 값 증가
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.replyInsert(vo);
	}

	@Override
	public List<ReplyVO> getReplyList(int bno) {
		log.info("---getReplyList"+bno);
		return mapper.getReplyList(bno);
	}

	@Override
	@Transactional
	public int replyDelete(int rno) {
		log.info("---replyDelete"+rno);
		
		ReplyVO rvo = mapper.replyRead(rno);
		if(rvo == null) {
			return 0;
		}
		boardMapper.updateReplyCnt(rvo.getBno(), -1);
		return mapper.replyDelete(rno);
	}
	

	@Override
	public ReplyVO replyRead(int rno) {
		log.info("---replyRead"+rno);
		return mapper.replyRead(rno);
	}

	@Override
	public int replyUpdate(ReplyVO vo) {
		log.info("---replyUpdate"+vo);
		return mapper.replyUpdate(vo);
	} 
	
	@Override
	public int deleteAllReply(int bno) {
		log.info("deleteing all reply...");
		return mapper.deleteAllReply(bno);
	}
}
