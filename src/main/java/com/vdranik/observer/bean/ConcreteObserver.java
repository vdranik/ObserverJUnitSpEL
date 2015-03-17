package com.vdranik.observer.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import com.vdranik.observer.bean.impl.Observer;

public class ConcreteObserver implements Observer, ApplicationContextAware, BeanNameAware, BeanFactoryAware, InitializingBean, ApplicationListener<ContextClosedEvent> {

	private String name;
	private String uuid;

	public ConcreteObserver() {
		System.out.println("create ConcreteObserver");
	}

	@Override
	public void notify(String message) {
		System.out.println(message);
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public ConcreteObserver(String name) {
		System.out.println("create ConcreteObserver");
		this.name = name;
		System.out.println("OBSERVER NAME " + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void init() {
		System.out.println(this + " init ConcreteOBSERVER");
	}

	public void destroy() {
		System.out.println(this + " destroy ConcreteOBSERVER");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(this + " afterPropertiesSet");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println(this + " BEAN FACTORY - " + beanFactory);
	}

	@Override
	public void setBeanName(String name) {
		System.out.println(this + " bean name " + name);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println(this + " applicationContext " + applicationContext);
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println(this + " IS CLOSED " + event);
	}
}