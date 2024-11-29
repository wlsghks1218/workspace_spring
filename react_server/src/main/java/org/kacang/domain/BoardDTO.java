package org.kacang.domain;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardDTO {
	private static final long serialVersionUID = 1L;
	
    private int bdno;
    private String title;
    private String writer;
    private String content;
    private Date writeDate;
}
