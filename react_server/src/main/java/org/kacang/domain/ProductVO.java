package org.kacang.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductVO {
	private int id;
	private String title;
	private String content;
	private int price;
	private String main;
	private String detail;
}
