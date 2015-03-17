package com.vdranik.observer.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ConcreteObserverTest.class, ConcreteSubjectTest.class, QATest.class, DevTest.class, AllProfilesTest.class })
public class SuiteTest {
}
