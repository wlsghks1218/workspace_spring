package org.kacang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kacang.domain.BoardDTO;
import org.kacang.domain.UserDTO;
import org.kacang.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{
	
	@Autowired
	TestMapper tmapper;
	
	@Override
	public List<BoardDTO> getBoardList() {
		return tmapper.getBoardList();
	}
	
	@Override
	public BoardDTO getBoardInfo(int idx) {
		return tmapper.getBoardInfo(idx);
	}
	
	@Override
	public int insertBoard(BoardDTO boardDTO) {
		return tmapper.insertBoard(boardDTO);
	}
	
	@Override
	public UserDTO authenticate(String userId, String userPw) {
		return tmapper.authenticate(userId, userPw);
	}
	
	@Override
	public boolean isUsernameDuplicate(String username) {
		int result = tmapper.isUsernameDuplicate(username); 
		return result>0;
	}
	
	@Override
	public boolean registerUser(UserDTO userDTO) {
		int result = tmapper.registerUser(userDTO);
		return result>0;
	}
	
	@Override
	public int updateBoard(int idx, String title, String content) {
        Map<String, Object> params = new HashMap();
        params.put("idx", idx);
        params.put("title", title);
        params.put("content", content);
		return tmapper.updateBoard(params);
	}
	
	@Override
	public int deleteBoard(int idx) {
		return tmapper.deleteBoard(idx);
	}
}
