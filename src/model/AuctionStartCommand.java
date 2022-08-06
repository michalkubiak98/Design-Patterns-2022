package model;

import javax.swing.*;

public class AuctionStartCommand implements Command{

    private final AuctionManager manager;

    public AuctionStartCommand() {
        this.manager = AuctionManager.getManager();
    }

    @Override
    public void executeCommand() {
        Auction auction = new Auction();
        manager.setAuction(auction);
        manager.setAuctionLaunched(false);
        double reservePrice = 0.0;
        String reservePriceString = JOptionPane.showInputDialog(null, "Enter reserve price:");
        try {
            reservePrice = Double.parseDouble(reservePriceString);
        } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Reserve price can only be number");
                return;
        } catch (NullPointerException e){
            return;
        }
        auction.setReservePrice(reservePrice);
    }
}
