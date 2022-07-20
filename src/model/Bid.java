package model;
import java.text.NumberFormat;


public class Bid {

	private final Observer bidder;
	private final double amount;

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
