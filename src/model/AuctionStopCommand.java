package model;

public class AuctionStopCommand implements Command{

    private final AuctionManager manager;

    public AuctionStopCommand() {
        this.manager = AuctionManager.getManager();
    }

    @Override
    public void executeCommand() {
        manager.getAuction().closeAuction();
    }
}
