package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.*;
import view.AuctionManagerView;

public class AuctionController implements ActionListener {

	private Auction auction;
	private final AuctionManager manager;
	private final AuctionManagerView view;
	private final Command launchCommand;
	private final Command vipCommand;
	private final Command normalCommand;
	private final Command closeCommand;


	public AuctionController(AuctionManagerView manager) {
		this.manager = AuctionManager.getManager();
		this.view = manager;
		launchCommand = new AuctionStartCommand();
		vipCommand = new AddVIPBidderCommand();
		normalCommand = new AddNormalBidderCommand();
		closeCommand = new AuctionStopCommand();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(view.getLaunchButton())) {
			manager.setCommand(launchCommand);
			view.launch();
		}else if (button.equals(view.getAddNormalBidderButton()))
			manager.setCommand(vipCommand);
		else if (button.equals(view.getAddVIPBidderButton()))
			manager.setCommand(normalCommand);
		else if (button.equals(view.getCloseAuctionButton())) {
			manager.setCommand(closeCommand);
			view.close();
		}
		manager.execute();
	}

}
