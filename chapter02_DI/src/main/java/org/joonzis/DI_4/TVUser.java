package org.joonzis.DI_4;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		
		// 1. Spring 컨테이너 구동
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("applicationContext4.xml");
		
		// 2. Spring 컨테이너로부터 객체 요청
		TV stv = (SamsungTV)ctx.getBean("stv");
		stv.powerOn();
		stv.powerOff();
		stv.volumeUp();
		stv.volumeDown();
		
	}
}
