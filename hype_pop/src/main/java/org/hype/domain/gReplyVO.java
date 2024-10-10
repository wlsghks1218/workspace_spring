package org.hype.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class gReplyVO {
	private int gReplyNo, gNo, userNo, gScore;
	private String gComment;
	Date gRegDate, gUpdateDate;
}
