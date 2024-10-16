package org.hype.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.hype.domain.gCatVO;
import org.hype.domain.goodsVO;
import org.hype.domain.rankVO;

public interface GoodsMapper {
	public List<goodsVO> getListByLikeCount();
	public List<rankVO> getCategoryRankNotLogin();
	public List<goodsVO> getListByInterestNotLogin(Map<String, String> map);
	public List<rankVO> getCategoryRankLogined();
	public List<goodsVO> getListByInterestLogined(Map<String, Object> map);
	public goodsVO getOneByGno(int gno);
	public List<goodsVO> getSearchList(@Param("searchText") String searchText, @Param("offset") int offset, @Param("limit") int limit);
	public gCatVO getCategory(int gno);
	public int getUpdatehit(goodsVO vo);
	public int getLike(@Param("userNo") int userNo,@Param("gno") int gno);
	public int insertLike(@Param("userNo") int userNo,@Param("gno") int gno);
	public int deleteLike(@Param("userNo") int userNo,@Param("gno") int gno);
	public int updateLikeCountPlus(@Param("gno") int gno);
	public int updatetLikeCountMinus(@Param("gno") int gno);
	public int getLikeCount(@Param("gno") int gno);
}
