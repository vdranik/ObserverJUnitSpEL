package com.vdranik.observer.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vdranik.observer.bean.ConcreteObserver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@ActiveProfiles({ "dev", "qa" })
public class AllProfilesTest {

	@Autowired
	AbstractApplicationContext context;

	@DirtiesContext
	@Test
	public void qaConcreteObserverTest() {
		ConcreteObserver concreteObserver = context.getBean("concreteObserver", ConcreteObserver.class);
		Assert.assertNotNull(concreteObserver);
		// remove concreteObserver Bean from context
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
		defaultListableBeanFactory.removeBeanDefinition("concreteObserver");
	}

	@Test
	public void devConcreteObserverDirtiesContextTest() {
		ConcreteObserver concreteObserver = context.getBean("concreteObserver", ConcreteObserver.class);
		Assert.assertNotNull(concreteObserver);
	}

	@IfProfileValue(name = "user.name", values = { "Volodymyr", "guest" })
	@Test
	public void onlySpecialUsersTest() {
		System.out.println("user.name=Volodymyr or guest");
	}
}