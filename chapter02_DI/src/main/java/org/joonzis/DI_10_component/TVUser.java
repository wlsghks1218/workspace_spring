package org.joonzis.DI_10_component;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		
		// 1. Spring 컨테이너 구동
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("applicationContext10.xml");
		
		// 2. Spring 컨테이너로부터 객체 요청
		// stv는 singleton, 출력 시 생성 문구문이 1번만 출력되며 같은 객체
//		TV tv = (TV)ctx.getBean("tv");
		TV tv = ctx.getBean("tv", TV.class);
		tv.powerOn();
		tv.powerOff();
		tv.volumeUp();
		tv.volumeDown();
	}
}
