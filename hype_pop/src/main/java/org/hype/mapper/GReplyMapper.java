package org.hype.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hype.domain.GReplyVO;

public interface GReplyMapper {
	public int insertGReply(GReplyVO gVo);
	public List<GReplyVO> getAllReplyList(@Param("gno") int gno,@Param("userNo") int userNo);
	public GReplyVO getMyReply(@Param("gno") int gno,@Param("userNo") int userNo);
	public double getAvgStars(int gno);
	public int chkReplied(int userNo);
	public int deleteReply(@Param("gno") int gno,@Param("userNo") int userNo);
	public int updateReply(GReplyVO vo);
	public int updateReplyCntPlus(@Param("gno") int gno);
	public int updateReplyCntMinus(@Param("gno") int gno);
	public List<GReplyVO> getAllReplyListWithPaging(@Param("gno") int gno, @Param("userNo") int userNo, @Param("offset") int offset, @Param("size") int size);
	public int getReplyCount(@Param("gno") int gno,@Param("userNo") int userNo);
}
