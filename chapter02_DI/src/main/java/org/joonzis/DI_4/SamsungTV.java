package org.joonzis.DI_4;

public class SamsungTV implements TV {
	private SonySpeaker sonySpeaker;
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
		sonySpeaker = new SonySpeaker();
		sonySpeaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		sonySpeaker = new SonySpeaker();
		sonySpeaker.volumeDown();
	}
}
