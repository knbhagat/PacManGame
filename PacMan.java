import java.util.NoSuchElementException;

/**
 * 
 * @author krishaanbhagat
 */
public class PacMan {
  protected Rooms currentRoom;
  protected Rooms [] adjrooms;

  public PacMan(Rooms startRoom) {
    this.currentRoom = startRoom;
    this.adjrooms = new Rooms[4];
    setAdjRooms();
  }
  
  public void setAdjRooms() {
    this.adjrooms[0] = currentRoom.getRoomToEast();
    this.adjrooms[1] = currentRoom.getRoomToNorth();
    this.adjrooms[2] = currentRoom.getRoomToSouth();
    this.adjrooms[3] = currentRoom.getRoomToWest();
  }


  public void goEast() {
    if (!canGoRight()) {
      throw new NoSuchElementException("Cannot go Right!");
    }

    this.currentRoom = this.currentRoom.getRoomToEast();
    setAdjRooms();
  }

  public boolean canGoRight() {
    if (this.currentRoom.getRoomToEast() != null) {
      return true;
    }

    return false;
  }

  public void goWest() {
    if (!canGoLeft()) {
      throw new NoSuchElementException("Cannot go Left!");
    }

    this.currentRoom = this.currentRoom.getRoomToWest();
    setAdjRooms();
  }

  public boolean canGoLeft() {
    if (this.currentRoom.getRoomToWest() != null) {
      return true;
    }

    return false;
  }

  public boolean canGoNorth() {
    if (this.currentRoom.getRoomToNorth() != null) {
      return true;
    }

    return false;
  }

  public void goNorth() {
    if (!canGoNorth()) {
      throw new NoSuchElementException("Cannot go North!");
    }

    this.currentRoom = this.currentRoom.getRoomToNorth();
    setAdjRooms();
  }

  public void goSouth() {
    if (!canGoSouth()) {
      throw new NoSuchElementException("Cannot go South!");
    }

    this.currentRoom = this.currentRoom.getRoomToSouth();
    setAdjRooms();
  }

  public boolean canGoSouth() {
    if (this.currentRoom.getRoomToSouth() != null) {
      return true;
    }

    return false;
  }
  
  public Rooms getRoom() {
    return this.currentRoom;
  }

}
