package model;

public class BidderFactory {

    public Bidder getBidder(String type, String name, Auction auction) {
        if (type == null) {
            return null;
        } else if (type.equals("VIP")) {
            return new VIPBidder(name, auction);
        } else if (type.equals("Normal")) {
            return new NormalBidder(name, auction);
        }
        return null;
    }
}
