package com.springbook.ioc.injection;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
public class CollectionBeanClient {
	public static void main(String[]args) {
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		CollectionBean bean =(CollectionBean) factory.getBean("collectionBean");
		List<String>addressList = bean.getAddressList();
		for(String address : addressList) {
			System.out.println(address.toString());
		}
		factory.close();
	}
}