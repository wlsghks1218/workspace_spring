package org.hype.service;

import java.util.List;

import org.hype.domain.GReplyVO;

public interface GReplyService {
	public int insertGReply(GReplyVO gVo);
	public List<GReplyVO> getAllReplyList(int gNo, int userNo);
	public GReplyVO getMyReply(int gNo, int userNo);
}
