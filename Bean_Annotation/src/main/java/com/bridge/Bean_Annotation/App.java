package com.bridge.Bean_Annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		
		  context.scan("com.bridge.Bean_Annotation");
		  context.refresh();
		 

		  //Getting Bean by Class
		MyDAOBean myDAOBean = context.getBean(MyDAOBean.class);
		System.out.println(myDAOBean);
	}
}
