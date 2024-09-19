package org.joonzis.DI_6;

public class AppleSpeaker implements Speaker{
	public AppleSpeaker() {
		System.out.println("==> AppleSpeaker 객체 생성");
	}

	@Override
	public void volumeUp() {
		System.out.println("AppleSpeaker -- 소리 up");
	}

	@Override
	public void volumeDown() {
		System.out.println("AppleSpeaker -- 소리 down");
	}
	
}
