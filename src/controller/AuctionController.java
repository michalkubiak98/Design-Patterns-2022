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
	private final BidderFactory factory;

	public AuctionController(AuctionManagerView manager) {
		this.manager = AuctionManager.getManager();
		this.view = manager;
		factory = new BidderFactory();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(view.getLaunchButton()))
			launchButton(0);
		else if (button.equals(view.getAddNormalBidderButton()))
			addBidder("Normal");
		else if (button.equals(view.getAddVIPBidderButton()))
			addBidder("VIP");
		else if (button.equals(view.getCloseAuctionButton()))
			close();
	}

	private void addBidder(String type) {
		String bidderName = JOptionPane.showInputDialog(view, "Enter name of the factory:");
		auction.addObserver(factory.getBidder(type, bidderName, auction));
	}

	private void close() {
		view.getLaunchButton().setEnabled(true);
		view.getAddNormalBidderButton().setEnabled(false);
		view.getAddVIPBidderButton().setEnabled(false);
		view.getCloseAuctionButton().setEnabled(false);
		auction.closeAuction();
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
