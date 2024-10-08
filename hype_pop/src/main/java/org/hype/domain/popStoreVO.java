package org.hype.domain;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class popStoreVO {

	private int psNo; // �˾������ ��ȣ
	private String psName; // �˾������ �̸�
	private pCatVO psCat; // �˾������ ī�װ�
	private Date psStartDate; // �˾������ ������
	private Date psEndDate; // �˾������ ������
	private String psAddress; // �˾������ �ּ�]
	private double latitude; // �˾������ ����
	private double longitude; // �˾������ �浵
	private String psExp; // �˾������ �����
	private List<pImgVO> psImg; // �˾������ ���� ������
	private int likeCount; // �˾������ ���ƿ� ��
	private String snsAd; // SNS �ּ�
	private String comInfo; // ���ֻ� ����
	private String transInfo; // ������
	private String parkinginfo; // ������ ����
	private double avgRating; // ��� ����
}
