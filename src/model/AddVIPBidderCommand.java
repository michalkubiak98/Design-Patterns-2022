package model;

import javax.swing.*;

public class AddVIPBidderCommand implements Command{

    private final AuctionManager manager;
    private final BidderFactory factory;

    public AddVIPBidderCommand() {
        this.manager = AuctionManager.getManager();
        factory = new BidderFactory();
    }


    @Override
    public void executeCommand() {
        String bidderName = JOptionPane.showInputDialog(null, "Enter name of the bidder:");
        if(bidderName == null)
            return;
        manager.getAuction().addObserver(factory.getBidder("VIP", bidderName, manager.getAuction()));
    }
}
