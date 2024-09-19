package org.joonzis.DI_1;

public class SamsungTV {
	public SamsungTV() {
		System.out.println("==> SamsungTV 객체 생성");
	}
	public void powerOn() {
		System.out.println("SamsungTV -- 전원 on");
	}
	public void powerOff() {
		System.out.println("SamsungTV -- 전원 off");
	}
	public void volumeUp() {
		System.out.println("SamsungTV -- 소리 up");
	}
	public void volumeDown() {
		System.out.println("SamsungTV -- 소리 down");
	}
}
