package org.hype.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hype.domain.GReplyVO;
import org.hype.mapper.GReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class GReplyServiceImpl implements GReplyService {
	@Autowired
	private GReplyMapper gMapper;
	
	@Override
	public int insertGReply(GReplyVO gVo) {
		return gMapper.insertGReply(gVo);
	}
	
	@Override
	public List<GReplyVO> getAllReplyList(@Param("gno") int gno,@Param("userNo") int userNo) {
		log.info("getAllReplyList에서 gno, userNo" + gno + userNo);
		return gMapper.getAllReplyList(gno, userNo);
	}
	
	@Override
	public GReplyVO getMyReply(@Param("gno") int gno,@Param("userNo") int userNo) {
		log.info("getMyReply에서 gno, userNo" + gno + userNo);
		return gMapper.getMyReply(gno, userNo);
	}
	
	@Override
	public double getAvgStars() {
		return gMapper.getAvgStars();
	}
	
	@Override
	public int chkReplied(int userNo) {
		return gMapper.chkReplied(userNo);
	}
}
