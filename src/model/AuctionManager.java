package model;

public class AuctionManager {

    private Auction auction;
    private boolean auctionLaunched = false;
    private static AuctionManager manager;
    private Command command;

    private AuctionManager(){}

    public static AuctionManager getManager() {
        if(manager == null)
            manager = new AuctionManager();
        return manager;
    }

    public Auction getAuction() {
        return auction;
    }

    public boolean isAuctionLaunched() {
        return auctionLaunched;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public void setAuctionLaunched(boolean auctionLaunched) {
        this.auctionLaunched = auctionLaunched;
    }

    public void execute(){
        command.executeCommand();
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
