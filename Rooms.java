import java.util.ArrayList;

public class Rooms {
  private int id;

  // Understand the adjacent Rooms
  private Rooms toLeft;
  private Rooms toRight;
  private Rooms toSouth;
  private Rooms toNorth;

  public Rooms(int id) {
    this.id = id;
  }

  public void addRoomToWest(Rooms add) {
    this.toLeft = add;
  }

  public void addRoomToEast(Rooms add) {
    this.toRight = add;
  }

  public void addRoomToNorth(Rooms add) {
    this.toNorth = add;
  }

  public void addRoomToSouth(Rooms add) {
    this.toSouth = add;
  }

  public int getID() {
    return this.id;
  }

  public Rooms getRoomToWest() {
    return this.toLeft;
  }

  public Rooms getRoomToEast() {
    return this.toRight;
  }

  public Rooms getRoomToNorth() {
    return this.toNorth;
  }

  public Rooms getRoomToSouth() {
    return this.toSouth;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Rooms) {
      Rooms otherRoom = (Rooms) other;
      return this.id == otherRoom.id;
    }

    return false;
  }
}
