package org.hype.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hype.domain.GReplyVO;

public interface GReplyMapper {
	public int insertGReply(GReplyVO gVo);
	public List<GReplyVO> getAllReplyList(@Param("gno") int gno,@Param("userNo") int userNo);
	public GReplyVO getMyReply(@Param("gno") int gno,@Param("userNo") int userNo);
	public double getAvgStars();
	public int chkReplied(int userNo);
}
