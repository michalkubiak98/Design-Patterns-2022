package view;

import controller.AuctionController;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class AuctionManagerView extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private final JButton launchButton;
    private final JButton addVIPBidderButton;
    private final JButton addNormalBidderButton;
    private final JButton closeAuctionButton;

    public AuctionManagerView() {
        super("Auction Manager");
        AuctionController con = new AuctionController(this);
        JPanel panel = new JPanel(new FlowLayout());
        launchButton = new JButton("Launch Auction");
        panel.add(launchButton);
        addVIPBidderButton = new JButton("Add VIP Bidder");
        panel.add(addVIPBidderButton);
        addVIPBidderButton.setEnabled(false);
        addNormalBidderButton = new JButton("Add Normal Bidder");
        panel.add(addNormalBidderButton);
        addNormalBidderButton.setEnabled(false);
        closeAuctionButton = new JButton("Close Auction");
        panel.add(closeAuctionButton);
        closeAuctionButton.setEnabled(false);
        launchButton.addActionListener(con);
        addNormalBidderButton.addActionListener(con);
        addVIPBidderButton.addActionListener(con);
        closeAuctionButton.addActionListener(con);
        add(panel);
    }

    public JButton getLaunchButton() {
        return launchButton;
    }

    public JButton getAddNormalBidderButton() {
        return addNormalBidderButton;
    }

    public JButton getAddVIPBidderButton() {
        return addVIPBidderButton;
    }

    public JButton getCloseAuctionButton() {
        return closeAuctionButton;
    }

    public void launch() {
        launchButton.setEnabled(false);
        addNormalBidderButton.setEnabled(true);
        addVIPBidderButton.setEnabled(true);
        closeAuctionButton.setEnabled(true);
    }

    public void close() {
        launchButton.setEnabled(true);
        addNormalBidderButton.setEnabled(false);
        addVIPBidderButton.setEnabled(false);
        closeAuctionButton.setEnabled(false);
    }
}
