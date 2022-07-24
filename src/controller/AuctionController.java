package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.*;
import view.AuctionManagerView;

public class AuctionController implements ActionListener {

	private Auction auction;
	private final AuctionManager manager;
	private final AuctionManagerView view;

	public AuctionController(AuctionManagerView manager) {
		this.manager = AuctionManager.getManager();
		this.view = manager;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(view.getLaunchButton()))
			launchButton(0);
		else if (button.equals(view.getAddNormalBidderButton()))
			addNormalBidder();
		else if (button.equals(view.getAddVIPBidderButton()))
			addVIPBidder();
		else if (button.equals(view.getCloseAuctionButton()))
			close();
	}

	private void close() {
		view.getLaunchButton().setEnabled(true);
		view.getAddNormalBidderButton().setEnabled(false);
		view.getAddVIPBidderButton().setEnabled(false);
		view.getCloseAuctionButton().setEnabled(false);
		auction.closeAuction();
	}

	private void addNormalBidder() {
		String bidderName = JOptionPane.showInputDialog(view, "Enter name of the bidder:");
		auction.addObserver(new NormalBidder(bidderName, auction));
	}

	private void addVIPBidder() {
		String bidderName = JOptionPane.showInputDialog(view, "Enter name of the bidder:");
		auction.addObserver(new VIPBidder(bidderName, auction));
	}

	private void launchButton(int count) {
		auction = new Auction();
		manager.setAuctionLaunched(false);
		double reservePrice = 0.0;
		String reservePriceString = JOptionPane.showInputDialog(view, "Enter reserve price:");
		try {
			reservePrice = Double.parseDouble(reservePriceString);
		} catch (NumberFormatException nfe) {
			if (count == 3)
				return;
			JOptionPane.showMessageDialog(null, "Reserve price can only be number");
			launchButton(count+1);
		} catch (NullPointerException e){
			return;
		}
		auction.setReservePrice(reservePrice);
		view.getLaunchButton().setEnabled(false);
		view.getAddNormalBidderButton().setEnabled(true);
		view.getAddVIPBidderButton().setEnabled(true);
		view.getCloseAuctionButton().setEnabled(true);
	}

}













/*
Bidder -> Normal Bidders and a VIP Bidder

the process of bidding was same like for both boidder
they wil bid and the bidding amount will be passed to auction and other function will be done

 */































