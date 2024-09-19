package org.joonzis.DI_5;

public class SamsungTV implements TV {
	private SonySpeaker sonySpeaker;
	public SamsungTV() {
		System.out.println("==> SamsungTV 객체(1) 생성");
	}
	public SamsungTV(SonySpeaker speaker) {
		System.out.println("==> SamsungTV 객체(2) 생성");
		this.sonySpeaker = speaker;
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
		sonySpeaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		sonySpeaker.volumeDown();
	}
}
