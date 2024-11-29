package org.kacang.mapper;

import java.util.List;
import java.util.Map;

import org.kacang.domain.BoardDTO;
import org.kacang.domain.UserDTO;

public interface TestMapper {
	public List<BoardDTO> getBoardList();
	public BoardDTO getBoardInfo(int idx);
	public int insertBoard(BoardDTO boardDTO);
	public UserDTO authenticate(String userId, String userPw);
	public int isUsernameDuplicate(String username);
	public int registerUser(UserDTO userDTO);
	public UserDTO read(String username);
	public int updateBoard(Map<String, Object> params);
	public int deleteBoard(int idx);
	public int deleteRememberMe(String username);
	public int insertRememberMe(Map<String, Object> params);
}
