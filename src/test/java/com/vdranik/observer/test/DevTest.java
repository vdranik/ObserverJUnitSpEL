package com.vdranik.observer.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vdranik.observer.bean.ConcreteObserver;
import com.vdranik.observer.bean.ConcreteSubject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@ActiveProfiles("dev")
public class DevTest {

	@Autowired
	AbstractApplicationContext context;
	@Value("${value1}")
	String firstName;

	@Test
	public void getPropertyTest() {
		Assert.assertEquals("Lenin", firstName);
	}

	@Timed(millis = 4000)
	@Repeat(40)
	@Test(expected = NoSuchBeanDefinitionException.class)
	public void qaConcreteObserverTest() {
		ConcreteObserver concreteObserver = context.getBean("concreteObserver", ConcreteObserver.class);
		Assert.assertNull(concreteObserver);
	}

	@Test
	public void devConcreteSubjectTest() {
		ConcreteSubject concreteSubject = context.getBean("concreteSubject", ConcreteSubject.class);
		Assert.assertNotNull(concreteSubject);
	}
}