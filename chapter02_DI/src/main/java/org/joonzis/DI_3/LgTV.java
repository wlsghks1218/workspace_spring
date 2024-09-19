package org.joonzis.DI_3;


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
