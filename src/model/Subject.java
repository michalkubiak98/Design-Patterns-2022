package model;

import view.Observer;

public interface Subject {
	void addObserver(Observer observer);

	void removeObserver(Observer observer);

	void notifyObeserver();

	void notifyObeserver(Observer observer);
}
