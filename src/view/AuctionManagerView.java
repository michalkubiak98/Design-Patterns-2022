package view;

import controller.AuctionController;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class AuctionManagerView extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private final JButton launchButton;
    private final JButton addBidderButton;
    private final JButton closeAuctionButton;

    public AuctionManagerView() {
        super("Auction Manager");
        AuctionController con = new AuctionController(this);
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

    public JButton getLaunchButton() {
        return launchButton;
    }

    public JButton getAddBidderButton() {
        return addBidderButton;
    }

    public JButton getCloseAuctionButton() {
        return closeAuctionButton;
    }

}
