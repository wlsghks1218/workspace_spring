package org.joonzis.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private int rno, bno;
	private String reply, replyer;
	private Date replyDate, updateDate;

}
