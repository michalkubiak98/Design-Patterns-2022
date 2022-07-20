package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Auction;
import model.AuctionManager;
import model.Bidder;
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
		else if (button.equals(view.getAddBidderButton()))
			addBidder();
		else if (button.equals(view.getCloseAuctionButton()))
			close();
	}

	private void close() {
		view.getLaunchButton().setEnabled(true);
		view.getAddBidderButton().setEnabled(false);
		view.getCloseAuctionButton().setEnabled(false);
		auction.closeAuction();
	}

	private void addBidder() {
		String bidderName = JOptionPane.showInputDialog(view, "Enter name of the bidder:");
		auction.addObserver(new Bidder(bidderName, auction));
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
		view.getAddBidderButton().setEnabled(true);
		view.getCloseAuctionButton().setEnabled(true);
	}

}
