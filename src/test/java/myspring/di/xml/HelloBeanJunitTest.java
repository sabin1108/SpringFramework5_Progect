package myspring.di.xml;

//Assertions 클래스의 모든 static 메서드를 임포트 한다.
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanJunitTest {
	BeanFactory factory;
	
	@BeforeEach
	void container() {
		//1. Bean Container 객체생성
		factory = new GenericXmlApplicationContext("classpath:spring-beans.xml");
	}
	
	@Test
	void hello() {
		//2. Hello Bean을 Container 에게 요청하기
		Hello helloById = (Hello)factory.getBean("hello");
		
		Hello helloByType = factory.getBean("hello", Hello.class);	
		
		//2개의 Hello Bean의 주소비교
		System.out.println(helloById == helloByType);
		//assertSame을 이용해서 주소 비교
		assertSame(helloById, helloByType);
		
		//setName() injection을 검증하기
		assertEquals("Hello 스프링", helloByType.sayHello());
		
		//setPrinter() Injection을 검증하기
		helloByType.print();
		
		//3. StringPrinter Bean을 요청하기
		Printer printer = factory.getBean("strPrinter", Printer.class);
		
		assertEquals("Hello 스프링", printer.toString());
		
	}

}
