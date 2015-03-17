package com.vdranik.observer.bean.impl;

import java.util.List;

public interface Subject {

	List<Observer> getObservers();

	void setObservers(List<Observer> observers);

	void addObserver(Observer observer);

	public void removeObserver(Observer observer);

	public void removeAllObservers();

	public void notifyObservers(String message);
}