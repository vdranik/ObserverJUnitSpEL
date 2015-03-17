package com.vdranik.observer.main;

import java.util.AbstractMap;
import java.util.List;
import java.util.UUID;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.vdranik.observer.bean.ConcreteObserver;
import com.vdranik.observer.bean.ConcreteSubject;
import com.vdranik.observer.bean.impl.Observer;
import com.vdranik.observer.bean.impl.Subject;

public class Start {

	public static void main(String[] args) {
		try {
			// ApplicationContext with XML
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			context.registerShutdownHook();
			Subject subject = context.getBean("concreteSubject1", ConcreteSubject.class);
			subject.notifyObservers("NOTIFY_1");

			// literal expressions
			ExpressionParser parser = new SpelExpressionParser();
			String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();
			double avogadrosNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();
			int maxValue = (Integer) parser.parseExpression("0X7FFFFFFF").getValue();
			boolean trueValue = (Boolean) parser.parseExpression("true").getValue();
			Object nullValue = parser.parseExpression("null").getValue();

			ConcreteSubject concreteSubject2 = new ConcreteSubject();
			concreteSubject2.addObserver(new AbstractMap.SimpleEntry<String, Observer>("3", new ConcreteObserver("OBSERVER 3")).getValue());
			concreteSubject2.addObserver(new AbstractMap.SimpleEntry<String, Observer>("4", new ConcreteObserver("OBSERVER 4")).getValue());
			concreteSubject2.notifyObservers("NOTIFY_2");

			EvaluationContext evaluationContext = new StandardEvaluationContext(concreteSubject2);
			List values = (List) parser.parseExpression("observers.![name]").getValue(evaluationContext);
			System.out.println("# values " + values);

			evaluationContext.setVariable("concreteSubjectName", helloWorld);
			parser.parseExpression("concreteSubjectName = #concreteSubjectName").getValue(evaluationContext);
			System.out.println("concreteSubjectName " + concreteSubject2.getConcreteSubjectName());

			ExpressionParser spelParser = new SpelExpressionParser();
			StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
			standardEvaluationContext.registerFunction("randomUUID", UUID.class.getDeclaredMethod("randomUUID"));
			UUID generatedUUID1 = spelParser.parseExpression("#randomUUID()").getValue(standardEvaluationContext, UUID.class);
			UUID generatedUUID2 = spelParser.parseExpression("#randomUUID()").getValue(standardEvaluationContext, UUID.class);

			concreteSubject2.addObserver(new ConcreteObserver(generatedUUID1.toString()));
			concreteSubject2.addObserver(new ConcreteObserver(generatedUUID2.toString()));
			concreteSubject2.notifyObservers("NOTIFY_3");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
