package model;

import java.util.ArrayList;

public class Auction implements Subject {

    private final ArrayList<Observer> birders;
    private Bid currentHigh;
    private double reservePrice;
    private boolean open;

    public Auction() {
        birders = new ArrayList<>();
        open = true;
        currentHigh = new Bid(null, 0);
    }

    public void setReservePrice(double price) {
        reservePrice = price;
    }

    public boolean bidderAlreadyPresent(Observer b) {
        return birders.contains(b);
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
        } else {
            ((Bidder) latestBid.getBidder()).displayMessage("Current bid is less then highest bid");
        }
    }

    public void closeAuction() {
        if (currentHigh.getAmount() >= reservePrice) {
            for (Observer bdr : birders) {
                if (bdr == currentHigh.getBidder()) {
                    ((Bidder) bdr).displayMessage("Congratulations, your bid has been accepted.");
                } else {
                    ((Bidder) bdr).displayMessage("Auction has closed");
                }
            }
        } else {
            for (Observer bdr : birders) {
                ((Bidder) bdr).displayMessage("Auction has closed");
            }
        }
        for (int i = 0; i < birders.size(); i++) {
            Observer bdr = birders.get(i);
            birders.remove(bdr);
            ((Bidder) bdr).withdraw();
        }
        open = false;
    }

    @Override
    public void addObserver(Observer observer) {
        if (!birders.contains(observer)) {
            birders.add(observer);
            for (Observer bdr : birders) {
                if (bdr != observer) {
                    ((Bidder) bdr).displayEntry(observer);
                } else {
                    for (Observer bd : birders) {
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
        if (birders.contains(observer)) {
            birders.remove(observer);
            ((Bidder) observer).withdraw();
            for (Observer bdr : birders) {
                if (bdr != observer) {
                    ((Bidder) bdr).displayWithdrawal(observer);
                }
            }
        }
    }

    @Override
    public void notifyObeserver() {
        for (Observer bdr : birders) {
            bdr.update(currentHigh);
        }
    }

    @Override
    public void notifyObeserver(Observer observer) {
        if (bidderAlreadyPresent(observer))
            observer.update(currentHigh);
    }
}
