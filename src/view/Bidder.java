package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.Auction;
import model.Bid;

/**
 * Bidder.java
 *
 * Bidders to the auction. They register themselves with the auction so that
 * they are notified when the state of the auction has changed. (i.e. someone
 * has put out a higher bid)
 */

public class Bidder extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Auction auction;
	private JTextArea info;
	private JScrollPane scroll;
	private JButton enterButton, bidButton, withdrawButton;

	public Bidder(String name, Auction auction) {
		super(name);
		this.name = name;
		this.auction = auction;
		JPanel panel = new JPanel(new BorderLayout());
		info = new JTextArea();
		info.setEditable(false);
		scroll = new JScrollPane(info);
		panel.add(scroll, BorderLayout.CENTER);

		enterButton = new JButton("Enter");
		bidButton = new JButton("Bid!");
		withdrawButton = new JButton("Withdraw");

		enterButton.setEnabled(false);
		bidButton.setEnabled(true);
		withdrawButton.setEnabled(true);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (auction.isOpen()) {
					register();
					enterButton.setEnabled(false);
					bidButton.setEnabled(true);
					withdrawButton.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(Bidder.this, "Auction closed");
				}
			}
		});
		bidButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (auction.isOpen()) {
					try {
						double bidValue = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter a bid"));
						makeBid(bidValue);
					} catch (NumberFormatException nf) {

					}
				} else {
					JOptionPane.showMessageDialog(Bidder.this, "Auction closed");
				}
			}
		});
		withdrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (auction.isOpen()) {
					withdraw();
				} else {
					JOptionPane.showMessageDialog(Bidder.this, "Auction closed");
				}
			}
		});

		JPanel panel2 = new JPanel();
		panel2.add(enterButton);
		panel2.add(bidButton);
		panel2.add(withdrawButton);

		panel.add(panel2, BorderLayout.SOUTH);

		add(panel);

		setSize(300, 400);
		setVisible(true);
	}

	public String getName() {
		return name;
	}

	public void register() {
		if (!auction.isOpen()) {
			JOptionPane.showMessageDialog(this, "Auction closed");
		} else if (!auction.bidderAlreadyPresent(this)) {
			auction.addObserver(this);
		}
	}

	public void makeBid(double bidValue) {
		auction.submitBid(new Bid(this, bidValue));
	}

	public void withdraw() {
		if (auction.bidderAlreadyPresent(this)) {
			auction.removeObserver(this);
		}
		enterButton.setEnabled(true);
		bidButton.setEnabled(false);
		withdrawButton.setEnabled(false);
	}

	public void display(Bid latestBid) {
		info.append("The latest bid is " + latestBid + "\n");
	}

	public void displayEntry(Observer b) {
		info.append(((Bidder) b).getName() + " has entered the auction\n");
	}

	public void displayWithdrawal(Observer b) {
		info.append(((Bidder) b).getName() + " has withdrawn from the auction\n");
	}

	public void displayPresence(Observer b) {
		info.append(((Bidder) b).getName() + " is already in the auction\n");
	}

	public void displayBidNotAccepted() {
		info.append("Bid is below reserve price\n");
	}

	public void displayMessage(String message) {
		info.append(message + "\n");
	}

	@Override
	public void update(Bid newState) {
		Bid latestBid = (Bid) newState;
		display(latestBid);
	}

}
