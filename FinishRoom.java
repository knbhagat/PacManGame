
public class FinishRoom extends Rooms{
  public String warning = "You have finished the game, you won!";
  
  public FinishRoom(int id) {
    super(id);
  }
  
  public String getWarning() {
    return this.warning;
  }
}
