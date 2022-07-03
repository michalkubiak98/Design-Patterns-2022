package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Auction;
import view.AuctionManager;
import view.Bidder;

public class Controller implements ActionListener {

	private Auction auction;
	private AuctionManager manager;

	public Controller(AuctionManager manager) {
		this.manager = manager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(manager.getLaunchButton()))
			launchButton();
		else if (button.equals(manager.getAddBidderButton()))
			addBidder();
		else if (button.equals(manager.getCloseAuctionButton()))
			close();
	}

	private void close() {
		manager.getLaunchButton().setEnabled(true);
		manager.getAddBidderButton().setEnabled(false);
		manager.getCloseAuctionButton().setEnabled(false);
		auction.closeAuction();
	}

	private void addBidder() {
		String bidderName = JOptionPane.showInputDialog(manager, "Enter name of the bidder:");
		auction.addObserver(new Bidder(bidderName, auction));
	}

	private void launchButton() {
		auction = new Auction();
		manager.setAuctionLaunched(false);
		double reservePrice = 0.0;
		String reservePriceString = JOptionPane.showInputDialog(manager, "Enter reserve price:");
		try {
			reservePrice = Double.parseDouble(reservePriceString);
		} catch (NumberFormatException nfe) {

		}
		auction.setReservePrice(reservePrice);
		manager.getLaunchButton().setEnabled(false);
		manager.getAddBidderButton().setEnabled(true);
		manager.getCloseAuctionButton().setEnabled(true);
	}

}
