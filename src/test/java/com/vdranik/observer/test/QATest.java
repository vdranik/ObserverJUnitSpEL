package com.vdranik.observer.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vdranik.observer.bean.ConcreteObserver;
import com.vdranik.observer.bean.ConcreteSubject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@TestPropertySource(value = "classpath:concreteObserver.properties", properties = { "customProp=test" })
@ActiveProfiles("qa")
public class QATest {

	@Autowired
	AbstractApplicationContext context;
	@Autowired
	Environment environment;

	@Test
	public void getPropertyTest() {
		Assert.assertEquals("test", environment.getProperty("customProp"));
	}

	@Test
	public void getPropertyTest2() {
		Assert.assertEquals("Lenin", environment.getProperty("value1"));
	}

	@Test
	public void qaConcreteObserverTest() {
		ConcreteObserver concreteObserver = context.getBean("concreteObserver", ConcreteObserver.class);
		Assert.assertNotNull(concreteObserver);
	}

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void devConcreteSubjectTest() {
		ConcreteSubject concreteSubject = context.getBean("concreteSubject", ConcreteSubject.class);
		Assert.assertNull(concreteSubject);
	}
}