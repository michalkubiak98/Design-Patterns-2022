package controller;

import model.Bid;
import model.Bidder;
import model.Observer;
import view.BidderView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BidderController implements ActionListener {

    private final Bidder bidder;
    private final BidderView view;

    public BidderController(Bidder bidder) {
        this.bidder = bidder;
        this.view = new BidderView(bidder.getName(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (view.getEnterButton(obj)) {
            if (bidder.getAuction().isOpen()) {
                register();
                view.enableBid();
            } else {
                JOptionPane.showMessageDialog(view, "Auction closed");
            }
        } else if (view.getBidButton(obj)) {
            if (bidder.getAuction().isOpen()) {
                try {
                    double bidValue = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter a bid"));
                    makeBid(bidValue);
                } catch (NumberFormatException nf) {
                    JOptionPane.showMessageDialog(null, "Bid can only be number");
                }catch (NullPointerException ex){}
            } else {
                JOptionPane.showMessageDialog(view, "Auction closed");
            }
        } else if (view.getWithdrawButton(obj)) {
            if (bidder.getAuction().isOpen()) {
                withdraw();
            } else {
                JOptionPane.showMessageDialog(view, "Auction closed");
            }
        }
    }

    public void makeBid(double bidValue) {
        bidder.getAuction().submitBid(new Bid(bidder, bidValue));
    }

    public void register() {
        if (!bidder.getAuction().isOpen()) {
            JOptionPane.showMessageDialog(view, "Auction closed");
        } else if (!bidder.getAuction().bidderAlreadyPresent(bidder)) {
            bidder.getAuction().addObserver(bidder);
        }
    }

    public void withdraw() {
        if (bidder.getAuction().bidderAlreadyPresent(bidder)) {
            bidder.getAuction().removeObserver(bidder);
        }
        view.disableBid();
    }

    public void display(Bid latestBid) {
        view.display("The latest bid is " + latestBid + "\n");
    }

    public void displayEntry(Observer b) {
        view.display(((Bidder) b).getName() + " has entered the auction\n");
    }

    public void displayWithdrawal(Observer b) {
        view.display(((Bidder) b).getName() + " has withdrawn from the auction\n");
    }

    public void displayPresence(Observer b) {
        view.display(((Bidder) b).getName() + " is already in the auction\n");
    }

    public void displayBidNotAccepted() {
        view.display("Bid is below reserve price\n");
    }

    public void displayMessage(String s) {
        view.display(s);
    }
}
