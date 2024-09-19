package org.joonzis.DI_10_component;

import org.springframework.stereotype.Component;

/*
 * java => @Component
 * xml => <bean class="org.joonzis.DI_10_component.LgTV">
 * 
 * java => @Component("tv")
 * xml => <bean id="tv" class="org.joonzis.DI_10_component.LgTV">
 * 
 * @Component와 @Configuration/@Bean은 기능이 비슷
 * @Component는 클래스 단위
 * @Bean은 메소드 단위
 */

@Component("tv")
public class LgTV implements TV {
	public LgTV() {
		System.out.println("==> LgTV 객체 생성");
	}

	@Override
	public void powerOn() {
		System.out.println("LgTV -- 전원 on");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV -- 전원 off");
	}

	@Override
	public void volumeUp() {
		System.out.println("LgTV -- 소리 up");		
	}

	@Override
	public void volumeDown() {
		System.out.println("LgTV -- 소리 down");
	}
}
