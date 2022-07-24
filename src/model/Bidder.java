package model;

import controller.BidderController;

public abstract class Bidder implements Observer{
    private final String name;
    private final Auction auction;
    protected final BidderController con;

    public Bidder(String name, Auction auction) {
        this.name = name;
        this.auction = auction;
        con =  new BidderController(this);
    }

    public String getName() {
        return name;
    }

    public Auction getAuction() {
        return auction;
    }

    public void displayEntry(Observer b) {
        con.displayEntry(b);
    }

    public void displayWithdrawal(Observer b) {
        con.displayWithdrawal(b);
    }

    public void displayPresence(Observer b) {
        con.displayPresence(b);
    }

    public void withdraw(){
        con.withdraw();
    }

    public void displayBidNotAccepted() {
        con.displayBidNotAccepted();
    }

    public void displayMessage(String s) {
        con.displayMessage(s);
    }
}
