package org.joonzis.DI_6;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		
		// 1. Spring 컨테이너 구동
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("applicationContext6.xml");
		
		// 2. Spring 컨테이너로부터 객체 요청
		// singleton으로 생성 후 System.out,println으로 출력 시 생성자가 static으로 최상단에 모인다.
		TV stv1 = (SamsungTV)ctx.getBean("stv1");
		stv1.powerOn();
		stv1.powerOff();
		stv1.volumeUp();
		stv1.volumeDown();
		stv1.showPrice();
		
		System.out.println("----------");
		
		TV stv2 = (SamsungTV)ctx.getBean("stv2");
		stv2.powerOn();
		stv2.powerOff();
		stv2.volumeUp();
		stv2.volumeDown();
		stv2.showPrice();
	}
}
