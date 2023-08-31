import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PacManGame {

  private boolean playerTurn = true; // if player or ghosts turn to move
  private int gamePlay = 0;// 0 if you keep going, 1 if done

  public PacManGame() {
  }

  /**
   * Creates the map
   * 
   * @return the maps of as room
   */
  public ArrayList<Rooms> map() {

    // Create the rooms
    Rooms room1 = new Rooms(1);
    Rooms room2 = new StartRoom(2);
    Rooms room3 = new Rooms(3);
    Rooms room4 = new CoinRooms(4);
    Rooms room5 = new Rooms(5);
    Rooms room6 = new CoinRooms(6);
    Rooms room7 = new Rooms(7);
    Rooms room8 = new FinishRoom(8);
    Rooms room9 = new Rooms(9);

    room1.addRoomToEast(room2); // Located each room with the adjacent room
    room1.addRoomToNorth(room4);

    room2.addRoomToEast(room3);
    room2.addRoomToNorth(room5);
    room2.addRoomToWest(room1);

    room3.addRoomToNorth(room6);
    room3.addRoomToWest(room2);

    room4.addRoomToEast(room5);
    room4.addRoomToNorth(room7);
    room4.addRoomToSouth(room1);

    room5.addRoomToWest(room4);
    room5.addRoomToEast(room6);
    room5.addRoomToNorth(room8);
    room5.addRoomToSouth(room2);

    room6.addRoomToNorth(room9);
    room6.addRoomToSouth(room3);
    room6.addRoomToWest(room5);

    room7.addRoomToEast(room8);
    room7.addRoomToSouth(room4);

    room8.addRoomToEast(room9);
    room8.addRoomToWest(room7);
    room8.addRoomToSouth(room5);

    room9.addRoomToSouth(room6);
    room9.addRoomToWest(room8);

    ArrayList<Rooms> map = new ArrayList<>(9);
    map.add(room1);
    map.add(room2);
    map.add(room3);
    map.add(room4);
    map.add(room5);
    map.add(room6);
    map.add(room7);
    map.add(room8);
    map.add(room9);


    return map;

  }

  /**
   * Assembles everything prior to the game
   */
  public void start() {
    ArrayList<Rooms> map = map();
    System.out.println("Welcome to PacMan!\nThere are 9 rooms total, PacMan must get to the last"
        + " room while finding the two rooms with coins\nDon't get caught by the ghost!\n");
    
    System.out.println("rooms are formatted:\n7 8 9\n4 5 6\n1 2 3");
    System.out.println("Ghost starts in room 8 while PacMan starts in room 2!\n");
    setUpGame(map);
  }

  /**
   * Setup before the player makes the first move
   * 
   * @param map
   */
  public void setUpGame(ArrayList<Rooms> map) {

    PacMan pm = new PacMan(map.get(1)); // PacMan starts in the start room
    ((StartRoom) pm.getRoom()).getWarning();

    Ghost g = new Ghost(map.get(7)); // sets ghost
    Scanner scnr = new Scanner(System.in);

    CoinRooms coinRoom1 = (CoinRooms) map.get(3);
    CoinRooms coinRoom2 = (CoinRooms) map.get(5);


    gamePlay(pm, g, scnr, coinRoom1, coinRoom2);
  }
  

  /**
   * The game play with user input and ghosts moves
   * 
   * @param p
   * @param g
   * @param scnr
   */
  public void gamePlay(PacMan p, Ghost g, Scanner scnr, CoinRooms r, CoinRooms r1) {

    while (this.gamePlay == 0) { // while loop which runs the game
      
      
      
      if (playerTurn) {
        playersMove(scnr, p);
        
        if (p.getRoom() instanceof CoinRooms) {
          System.out.println(((CoinRooms) p.getRoom()).message());
        }

        if (p.getRoom() instanceof FinishRoom) {

          if (r.getCounter() == 1 && r1.getCounter() == 1) {
            System.out.println(((FinishRoom) p.getRoom()).getWarning());
            this.gamePlay = 1;
            break;
          } else {
            System.out.println("Need to find Coins");
          }
        }
        
      } else {
        ghostMove(g);
      }

      if (p.getRoom().equals(g.getRoom())) { // if in same room, you lose
        this.gamePlay = 1;
        System.out.println("HAHA YOU LOSE!");
        break;
      }
      
      for (int i = 0; i < 4; i++) {
        if (p.adjrooms[i] != null) {
          if (p.adjrooms[i].equals(g.currentRoom)) {
            System.out.println("Ghost is in an adjacent room!");
          }
        }
      }
    }
  }

  /**
   * the players move
   * 
   * @param scnr
   * @param p
   */
  public void playersMove(Scanner scnr, PacMan p) {
    System.out.println("Press N to move North");
    System.out.println("Press S to move South");
    System.out.println("Press E to move East");
    System.out.println("Press W to move West");
    boolean flag = false;

    while (!flag) {

      try { // Error will be caught if the player cannot move that direction

        switch (scnr.next()) { // let PacMan move wherever the user input goes
          case "N":
            p.goNorth();
            flag = true;
            break;

          case "S":
            p.goSouth();
            flag = true;
            break;

          case "E":
            p.goEast();
            flag = true;
            break;

          case "W":
            p.goWest();
            flag = true;
            break;

          default:
            System.out.println("Wrong input, Try Again");
            break;
        }

      } catch (NoSuchElementException nsee) {
        System.out.println(nsee.getMessage() + " Try Again!");
      }

    }

    System.out.println("Pacman is in room " + p.getRoom().getID());
    this.playerTurn = false;
  }

  /**
   * The ghosts move
   * 
   * @param g
   */
  public void ghostMove(Ghost g) {
    g.move();
    System.out.println("Ghost is in room " + g.currentRoom.getID());
    this.playerTurn = true;
  }
  
  public static void main (String args []) {
    PacManGame pmg = new PacManGame();
    pmg.start();
  }

}
