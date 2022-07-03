package view;

public class Main {
  public static void main(String[] args) {
    AuctionManager am = AuctionManager.getManager();
    am.setLocationRelativeTo(null);
    am.setSize(250, 100);
    am.pack();
    am.setVisible(true);
  }
}
