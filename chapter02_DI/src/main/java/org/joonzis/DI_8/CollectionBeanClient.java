package org.joonzis.DI_8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {
	public static void main(String[] args) {
		
		// 1. Spring 컨테이너 구동
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("applicationContext8.xml");
		
		// List 객체 가져오기
		// 아래 두개 다 같은 방식
		// CollectionBean bean1 = (CollectionBean)ctx.getBean("cBean1"); 
		CollectionBean bean1 = ctx.getBean("cBean1", CollectionBean.class);
		List<String> list = bean1.getAddressList();
		for(String addressList : list) {
			System.out.println(addressList);
		}
		
		System.out.println("----------------------------");
		
		CollectionBean bean2 = ctx.getBean("cBean2", CollectionBean.class);
		Set<String> set = bean2.getAddressSet();
		Iterator<String> itr = set.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		System.out.println("----------------------------");
		
		CollectionBean bean3 = ctx.getBean("cBean3", CollectionBean.class);
		Map<String, String> map = bean3.getAddressMap();
		set = map.keySet();
		itr = set.iterator();
		while(itr.hasNext()) {
			String k = itr.next();
			String v = map.get(k);
			System.out.println(k + ":" + v);
		}
	}
}
