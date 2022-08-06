package view;

import controller.BidderController;
import model.Bid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Bidder.java
 * <p>
 * Bidders to the auction. They register themselves with the auction so that
 * they are notified when the state of the auction has changed. (i.e. someone
 * has put out a higher bid)
 */

public class BidderView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JTextArea info;
    private final JButton enterButton, bidButton, withdrawButton;

    public BidderView(String name, BidderController con) {
        super(name);
        JPanel panel = new JPanel(new BorderLayout());
        info = new JTextArea();
        info.setEditable(false);
        JScrollPane scroll = new JScrollPane(info);
        panel.add(scroll, BorderLayout.CENTER);

        enterButton = new JButton("Enter");
        bidButton = new JButton("Bid!");
        withdrawButton = new JButton("Withdraw");

        enterButton.setEnabled(false);
        bidButton.setEnabled(true);
        withdrawButton.setEnabled(true);

        enterButton.addActionListener(con);
        bidButton.addActionListener(con);
        withdrawButton.addActionListener(con);

        JPanel panel2 = new JPanel();
        panel2.add(enterButton);
        panel2.add(bidButton);
        panel2.add(withdrawButton);

        panel.add(panel2, BorderLayout.SOUTH);

        add(panel);

        setSize(300, 400);
        setVisible(true);
    }

    public boolean getEnterButton(Object obj) {
        return enterButton.equals(obj);
    }

    public boolean getBidButton(Object obj) {
        return bidButton.equals(obj);
    }

    public boolean getWithdrawButton(Object obj) {
        return withdrawButton.equals(obj);
    }

    public void display(String latestBid) {
        info.append(latestBid);
    }

    public void enableBid() {
        enterButton.setEnabled(false);
        bidButton.setEnabled(true);
        withdrawButton.setEnabled(true);
    }

	public void disableBid() {
		enterButton.setEnabled(true);
		bidButton.setEnabled(false);
		withdrawButton.setEnabled(false);
	}
}
