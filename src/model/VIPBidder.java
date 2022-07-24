package model;

public class VIPBidder extends Bidder{

    public VIPBidder(String name, Auction auction) {
        super(name, auction);
    }

    @Override
    public void update(Bid bid) {
        con.display("The latest bid is " + bid + "\n");
    }
}
