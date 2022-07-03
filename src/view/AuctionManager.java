package view;

/**
 * AuctionManager.java
 *
 * Provide an auction whereby bidders register with the Auction.
 * Bidders may then submit bids and all bidders are notified when 
 * a bid is made.
 */

import java.awt.*;

import javax.swing.*;

import controller.Controller;
import model.Auction;

public class AuctionManager extends JFrame {
	private static AuctionManager manager;
	private static final long serialVersionUID = 1L;
	private boolean auctionLaunched = false;
	private Auction auction;
	private JButton launchButton;
	private JButton addBidderButton;
	private JButton closeAuctionButton;
	private Controller con;

	private AuctionManager() {
		super("Auction Manager");
		con = new Controller(this);
		JPanel panel = new JPanel(new FlowLayout());
		launchButton = new JButton("Launch Auction");
		panel.add(launchButton);
		addBidderButton = new JButton("Add Bidder");
		panel.add(addBidderButton);
		addBidderButton.setEnabled(false);
		closeAuctionButton = new JButton("Close Auction");
		panel.add(closeAuctionButton);
		closeAuctionButton.setEnabled(false);

		launchButton.addActionListener(con);

		addBidderButton.addActionListener(con);

		closeAuctionButton.addActionListener(con);

		add(panel);

	}

	/**
	 * @return the manager
	 */
	public static AuctionManager getManager() {
		if (manager == null)
			manager = new AuctionManager();
		return manager;
	}

	/**
	 * @return the auctionLaunched
	 */
	public boolean isAuctionLaunched() {
		return auctionLaunched;
	}

	/**
	 * @return the auction
	 */
	public Auction getAuction() {
		return auction;
	}

	/**
	 * @param auctionLaunched the auctionLaunched to set
	 */
	public void setAuctionLaunched(boolean auctionLaunched) {
		this.auctionLaunched = auctionLaunched;
	}

	/**
	 * @param auction the auction to set
	 */
	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	/**
	 * @return the launchButton
	 */
	public JButton getLaunchButton() {
		return launchButton;
	}

	/**
	 * @return the addBidderButton
	 */
	public JButton getAddBidderButton() {
		return addBidderButton;
	}

	/**
	 * @return the closeAuctionButton
	 */
	public JButton getCloseAuctionButton() {
		return closeAuctionButton;
	}

}
