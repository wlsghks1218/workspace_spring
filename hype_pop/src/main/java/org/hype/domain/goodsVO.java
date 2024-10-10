package org.hype.domain;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class goodsVO {
	private int gNo, psNo, gPrice, gHit, likeCount, replyCount;
	private String gName, gExp;
	private Date sellDate;
	private List<gImgVO> attachList;
	private gCatVO gCat;
}
