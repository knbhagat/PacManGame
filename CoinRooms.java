
public class CoinRooms extends Rooms {
  private int counter = 0;
  private String warning = "You have picked up A Coin!";
  
  public CoinRooms (int id) {
    super(id);
  }
  
  public String message() {
    if (counter != 0) {
      return "You have already picked up this coin!";
    } else {
      ++this.counter;
      return warning;
    }
  }
  
  public int getCounter() {
    return this.counter;
  }
}
