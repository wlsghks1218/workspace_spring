package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.ReplyVO;

public interface ReplyService {
	// 댓글 삽입
	public int replyInsert(ReplyVO vo);
	// 댓글 목록
	public List<ReplyVO> getReplyList(int bno);
	// 댓글 읽기
	public ReplyVO replyRead(int rno);
	// 댓글 삭제
	public int replyDelete(int rno);
	// 댓글 수정
	public int replyUpdate(ReplyVO vo);
	// 모든 댓글 삭제
	public int deleteAllReply(int bno);
}
