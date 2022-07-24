package model;

import java.text.NumberFormat;

public class NormalBidder extends Bidder{

    public NormalBidder(String name, Auction auction) {
        super(name, auction);
    }

    @Override
    public void update(Bid bid) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        con.display("The latest bid is " + nf.format(bid.getAmount()) + "\n");
    }
}
