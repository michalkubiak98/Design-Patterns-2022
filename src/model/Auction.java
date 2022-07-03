package model;

/**
 * Auction.java
 *
 * This manages an auction whereby bidders register themselves
 * and  are notified when a bid is made.
 */

import java.util.ArrayList;

import view.Bidder;
import view.Observer;

public class Auction implements Subject {

	private ArrayList<Observer> bidders;
	private Bid currentHigh;
	private double reservePrice;
	private boolean open;

	public Auction() {
		bidders = new ArrayList<Observer>();
		open = true;
		currentHigh = new Bid(null, 0);
	}

	public void setReservePrice(double price) {
		reservePrice = price;
	}

	public boolean bidderAlreadyPresent(Observer b) {
		return bidders.contains(b);
	}

	public boolean isOpen() {
		return open;
	}

	public void submitBid(Bid latestBid) {
		if (latestBid.getAmount() < reservePrice) {
			((Bidder) latestBid.getBidder()).displayBidNotAccepted();
		} else if (latestBid.getAmount() > currentHigh.getAmount()) {
			currentHigh = latestBid;
			notifyObeserver();
		}
	}

	public void closeAuction() {
		if (currentHigh.getAmount() >= reservePrice) {
			for (int i = 0; i < bidders.size(); i++) {
				Observer bdr = bidders.get(i);
				if (bdr == currentHigh.getBidder()) {
					((Bidder) bdr).displayMessage("Congratulations, your bid has been accepted.");
				} else {
					((Bidder) bdr).displayMessage("Auction has closed");
				}
			}
		} else {
			for (int i = 0; i < bidders.size(); i++) {
				Observer bdr = bidders.get(i);
				((Bidder) bdr).displayMessage("Auction has closed");
			}
		}
		for (int i = 0; i < bidders.size(); i++) {
			Observer bdr = bidders.get(i);
			bidders.remove(bdr);
			((Bidder) bdr).withdraw();
		}
		open = false;
	}

	@Override
	public void addObserver(Observer observer) {
		if (bidders.contains(observer))
			;
		else {
			bidders.add(observer);
			for (Observer bdr : bidders) {
				if (bdr != observer) {
					((Bidder) bdr).displayEntry(observer);
				} else {
					for (Observer bd : bidders) {
						if (bd != observer) {
							((Bidder) observer).displayPresence(bd);
						}
					}
					if (currentHigh.getBidder() != null) {
						observer.update(currentHigh);
					}
				}
			}
		}

	}

	@Override
	public void removeObserver(Observer observer) {
		if (bidders.contains(observer)) {
			bidders.remove(observer);
			((Bidder) observer).withdraw();
			for (Observer bdr : bidders) {
				if (bdr != observer) {
					((Bidder) bdr).displayWithdrawal(observer);
				}
			}
		}
	}

	@Override
	public void notifyObeserver() {
		for (int i = 0; i < bidders.size(); i++) {
			Observer bdr = bidders.get(i);
			bdr.update(currentHigh);
		}
	}

	@Override
	public void notifyObeserver(Observer observer) {
		if (bidderAlreadyPresent(observer))
			observer.update(currentHigh);
	}
}
