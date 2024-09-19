package org.joonzis.DI_7;

public class SamsungTV implements TV {
	private Speaker speaker;
	private int price;
	
	public SamsungTV() {
		System.out.println("==> SamsungTV 객체(1) 생성");
	}
	public void setSpeaker(Speaker speaker) {
		System.out.println("==> setSpeaker() 호출");
		this.speaker = speaker;
	}
	public void setPrice(int price) {
		System.out.println("==> setPrice() 호출");
		this.price = price;
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
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}
	@Override
	public void showPrice() {
		System.out.println("SaumsungTV 가격 ---" + price);
	}
}
