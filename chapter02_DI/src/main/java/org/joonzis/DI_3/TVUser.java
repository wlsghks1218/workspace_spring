package org.joonzis.DI_3;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		
		// 1. Spring 컨테이너 구동
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("applicationContext3.xml");
		
		// 2. Spring 컨테이너로부터 객체 요청
		// stv는 singleton, 출력 시 생성 문구문이 1번만 출력되며 같은 객체
		TV stv1 = (SamsungTV)ctx.getBean("stv");
		TV stv2 = (SamsungTV)ctx.getBean("stv");
		stv1.powerOn();
		stv1.powerOff();
		stv1.volumeUp();
		stv1.volumeDown();
		
		System.out.println("----------");
		
		// ltv는 prototype, 출력 시 생성 문구문이 생성한 만큼 출력되며 다른 객체
		TV ltv1 = (LgTV)ctx.getBean("ltv");
		TV ltv2 = (LgTV)ctx.getBean("ltv");
		ltv1.powerOn();
		ltv1.powerOff();
		ltv1.volumeUp();
		ltv1.volumeDown();
		
		System.out.println(stv1 == stv2 ? "같은 객체" : "다른 객체");
		System.out.println(ltv1 == ltv2 ? "같은 객체" : "다른 객체");
	}
}
