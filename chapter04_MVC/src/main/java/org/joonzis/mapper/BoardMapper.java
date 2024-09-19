package org.joonzis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;

public interface BoardMapper {
	// 전체 데이터 조회
	public List<BoardVO> getListWithPaging(Criteria cri);
	public int getTotalRecordCount();
	// 데이터 삽입
	public int insert(BoardVO bVo);
	// 단일 데이터 조회 - bno 값으로 데이터 조회
	public BoardVO read(int bno);
	// 데이터 삭제 - bno 값으로 삭제
	public int delete(int bno);
	// 데이터 삭제 - 특정 bno의 title, content, write 수정
	public int update(BoardVO bVo);
	// 댓글 수 수정
	public int updateReplyCnt(@Param("bno") int bno, @Param("amount") int amount);
}
