package org.hype.domain;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GReplyVO {
	private int gReplyNo, gNo, userNo, gScore;
	Date gRegDate, gUpdateDate;
	private String gComment;
}
	