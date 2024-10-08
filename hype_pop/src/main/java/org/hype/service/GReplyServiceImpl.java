package org.hype.service;

import org.hype.domain.GReplyVO;
import org.hype.mapper.GReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GReplyServiceImpl implements GReplyService {
	@Autowired
	private GReplyMapper gMapper;
	
	@Override
	public int insertGReply(GReplyVO gVo) {
		return gMapper.insertGReply(gVo);
	}
}
