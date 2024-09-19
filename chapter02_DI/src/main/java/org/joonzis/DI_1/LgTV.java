package org.joonzis.DI_1;

public class LgTV {
	public LgTV() {
		System.out.println("==> LgTV 객체 생성");
	}
	public void powerOn() {
		System.out.println("LgTV -- 전원 on");
	}
	public void powerOff() {
		System.out.println("LgTV -- 전원 off");
	}
	public void volumeUp() {
		System.out.println("LgTV -- 소리 up");
	}
	public void volumeDown() {
		System.out.println("LgTV -- 소리 down");
	}
}
