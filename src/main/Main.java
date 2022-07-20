package main;

import view.AuctionManagerView;

public class Main {
    public static void main(String[] args) {
        AuctionManagerView am = new AuctionManagerView();
        am.setLocationRelativeTo(null);
        am.setSize(250, 100);
        am.pack();
        am.setVisible(true);
    }
}
