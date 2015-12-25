package net.slipp.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDiMessageRendererClient {
	public static void main(String[] args) {
//		spring di 실습하기. 
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("/di.xml");
		MessageRenderer rendererSpringDi = (MessageRenderer) ac.getBean("messageRenderer");
		rendererSpringDi.dirender();
	}
}
