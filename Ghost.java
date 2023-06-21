import java.util.Random;

public class Ghost extends PacMan {
  private Random randGen;

  public Ghost(Rooms start) {
    super(start);
    this.randGen = new Random();
  }

  /**
   * Moves ghost randomly
   */
  public void move() {
    boolean flag = false;
    
    while (flag == false) {
      int randNum = randGen.nextInt(4);

      switch (randNum) {
        case 0:
          if (canGoSouth()) {
            goSouth();
            flag = true;
          }
          break;
        case 1:
          if (canGoLeft()) {
            goWest();
            flag = true;
          }
          break;
        case 2:
          if (canGoRight()) {
            goEast();
            flag = true;
          }
          break;
        default:
          if (canGoNorth()) {
            goNorth();
            flag = true;
          }
          break;
      }
    }
  }
}
