package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;
import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;

public interface BoardService {
	// 전체 리스트
	public List<BoardVO> getListWithPaging(Criteria cri);
	public int getTotalRecordCount();
	// 데이터 삽입
	public int register(BoardVO vo);
	// 데이터 수정
	public int modify(BoardVO vo);
	// 데이터 삭제
	public int remove(int bno); 
	// 단일 데이터
	public BoardVO get(int bno);
	
	// 첩부 파일 리스트
	public List<BoardAttachVO> getAttachList(int bno);
	
	// 첨부 파일 삭제
	public void deleteAttach(String uuid);
	
	// 모든 첩무 파일 삭제
	public void deleteAllAttach(int bno);
}
