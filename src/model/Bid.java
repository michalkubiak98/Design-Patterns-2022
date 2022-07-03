package model;
import java.text.NumberFormat;

import view.Bidder;
import view.Observer;

/**
 * Bid.java
 *
 * A bid for the auction.
 */

public class Bid {

	private Observer bidder;
	private double amount;

	public Bid(Observer bidder, double amount) {
		this.bidder = bidder;
		this.amount = amount;
	}

	public Observer getBidder() {
		return bidder;
	}

	public double getAmount() {
		return amount;
	}

	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return "Bidder: " + ((Bidder) bidder).getName() + " " + nf.format(amount);
	}
}
