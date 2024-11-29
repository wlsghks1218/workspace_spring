package org.kacang.service;

import java.util.List;

import org.kacang.domain.BoardDTO;
import org.kacang.domain.UserDTO;

public interface TestService {
	public List<BoardDTO> getBoardList();
	public BoardDTO getBoardInfo(int idx);
	public int insertBoard(BoardDTO boardDTO);
	public UserDTO authenticate(String userId, String userPw);
	public boolean isUsernameDuplicate(String username);
	public boolean registerUser(UserDTO userDTO);
	public int updateBoard(int idx, String title, String content);
	public int deleteBoard(int idx);
}
