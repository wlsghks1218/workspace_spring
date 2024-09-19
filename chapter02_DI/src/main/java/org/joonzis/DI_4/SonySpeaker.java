package org.joonzis.DI_4;

public class SonySpeaker {
	public SonySpeaker() {
		System.out.println("==> SonySpeaker 객체 생성");
	}
	public void volumeUp() {
		System.out.println("SonySpeaker -- 소리 up");
	}
	public void volumeDown() {
		System.out.println("SonySpeaker -- 소리 down");
	}
}
