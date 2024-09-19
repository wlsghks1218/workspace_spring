package org.joonzis.DI_7;

public class SonySpeaker implements Speaker {
	public SonySpeaker() {
		System.out.println("==> SonySpeaker 객체 생성");
	}
	@Override
	public void volumeUp() {
		System.out.println("SonySpeaker -- 소리 up");
	}
	@Override
	public void volumeDown() {
		System.out.println("SonySpeaker -- 소리 down");
	}
}
