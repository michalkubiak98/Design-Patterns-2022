package view;

import model.Bid;

public interface Observer {
	void update(Bid bid);
}
