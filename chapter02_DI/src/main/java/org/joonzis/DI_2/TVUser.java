package org.joonzis.DI_2;

public class TVUser {
	public static void main(String[] args) {
		TV stv = new SamsungTV(); // interface 사용 시 upCasting이 가능하다.
		stv.powerOn();
		stv.powerOff();
		stv.volumeUp();
		stv.volumeDown();
		
		System.out.println("----------");
		
		TV ltv = new LgTV();
		ltv.powerOn();
		ltv.powerOff();
		ltv.volumeUp();
		ltv.volumeDown();
	}
}
