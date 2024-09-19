package org.joonzis.DI_2;

public class SamsungTV implements TV {
	public SamsungTV() {
		System.out.println("==> SamsungTV 객체 생성");
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTV -- 전원 on");
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV -- 전원 off");
	}

	@Override
	public void volumeUp() {
		System.out.println("SamsungTV -- 소리 up");
	}

	@Override
	public void volumeDown() {
		System.out.println("SamsungTV -- 소리 down");
	}
}
