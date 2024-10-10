package org.hype.domain;
import java.sql.Date;
import lombok.Data;

@Data
public class GReplyVO {
	private int gReplyNo, gNo, userNo, gScore;
	private String gComment;
	Date gRegDate, gUpdateDate;
}
	