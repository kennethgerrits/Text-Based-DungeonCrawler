import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Room {
	// creates an arraylist of items to be stored in.
	private ArrayList<Item> items;

	// creates a hashmap which can hold rooms and their possible directions
	private HashMap<String, Room> rooms;

	// a roomnumber is used to identify rooms.
	private int roomNumber;

	// a room description is used to give the player a better playing expierence.
	private String description;

	// creates an arraylist for the enemies to be stored in.
	private ArrayList<Enemy> enemies;

	// gives us the ability to create random effects.
	Random r = new Random();

	// this is the constructor, a hashmap is needed for the rooms and the items are
	// needed to be stored too.
	public Room(int roomNumber) {
		rooms = new HashMap<String, Room>();
		items = new ArrayList<Item>();
		this.roomNumber = roomNumber;
		enemies = new ArrayList<Enemy>();
	}

	// you can add an enemy to the arraylist with this method.
	public void addEnemy(Enemy Enemy) {
		enemies.add(Enemy);
	}

	// you can add a room to your hashmap of rooms, followed by a possible direction
	// the user can go to.
	public void addRoom(String direction, Room room) {
		if (rooms == null) {
			rooms = new HashMap<>();
		}
		rooms.put(direction, room);
	}

	// returns the description of a room.
	public String getDescription() {
		return description;
	}

	// sets the description of a room.
	public void setDescription(String description) {
		this.description = description;
	}

	// adds an item to the arraylist.
	public void addItem(Item item) {
		items.add(item);
	}

	// prints information about the items and exits in the room.
	public void look() {
		System.out.print("The following items are in the room: ");
		items.forEach(i -> System.out.print("[" + i.getName() + "]" + " "));
		System.out.println("");
		System.out.print("You can go in the following directions: ");
		rooms.keySet().forEach(i -> System.out.print("[" + i + "]" + " "));
		System.out.println("");
	}

	// prints information about the room.
	public void roomInformation() {
		System.out.println(printRoomName());
		System.out.println("This room is " + description);
		lookEnemy();
	}

	// looks if there's an enemy added to the room (and arraylist)
	public void lookEnemy() {
		for (Enemy enemy : enemies) {
			System.out.println("You've encountered a " + enemy.getStatus() + " [" + enemy.getName() + "]");
		}
	}

	// kills the enemy targeted by the player.
	public Enemy killEnemy(String enemyName) {
		System.out.println("You've slain a " + enemyName);
		for (Enemy enemy : enemies) {
			if (enemy.getName().equals(enemyName)) {
				enemy.setStatus("dead");
				return enemy;
			}
		}
		return null;
	}

	// checks if a dead enemy can be looted, if so make a 50% chance to drop a coin.
	// Then remove the dead enemy from the room.
	public Enemy lootEnemy(String enemyName) {
		Item coin = new Item("Coin");
		boolean dropChance = r.nextBoolean();

		for (Enemy enemy : enemies) {
			if (enemy.getStatus().equals("dead") && enemy.getName().equals(enemyName)) {
				enemies.remove(enemy);
				if (dropChance) {
					System.out
							.println("The " + enemyName + " has dropped a coin! Try to look in the room and find it!");
					addItem(coin);
				} else {
					System.out.println("This poor fella didn't carry anything.");
				}
				return enemy;
			}
		}
		return null;
	}

	// looks in which room the user is, then prints the title.
	public String printRoomName() {
		switch (roomNumber) {
		case 0:
			return "You're currently in room " + (roomNumber + 1) + ": The main entrance";

		case 1:
			return "You're currently in room " + (roomNumber + 1) + ": The dark Xen.";

		case 2:
			return "You're currently in room " + (roomNumber + 1) + ": The Connector.";

		case 3:
			return "You're currently in room " + (roomNumber + 1) + ": The arena.";

		case 4:
			return "You're currently in room " + (roomNumber + 1) + ": The Munchings.";

		case 5:
			return "You're currently in room " + (roomNumber + 1) + ": The mainhall.";

		case 6:
			return "You're currently in room " + (roomNumber + 1) + ": The foundation of life.";

		case 7:
			return "You're currently in room " + (roomNumber + 1) + ": The Tuan.";

		case 8:
			return "You're currently in room " + (roomNumber + 1) + ": The treasure room.";

		case 9:
			return "You're currently in room " + (roomNumber + 1) + ": The small Mon.";
		}

		return "Invalid room";
	}

	// returns the roomnumber.
	public int getRoomNumber() {
		return roomNumber;
	}

	// returns a random room which lays next to this room.
	public Room getRandomNearRoom() {
		ArrayList<String> valuesList = new ArrayList<String>(rooms.keySet());
		int randomIndex = r.nextInt(valuesList.size());
		String randomValue = valuesList.get(randomIndex);
		return rooms.getOrDefault(randomValue, null);
	}

	// returns the room associated with the direction, else return null
	public Room openDoor(String direction) {
		return rooms.getOrDefault(direction, null);
	}

	// returns the item picked up, else return null.
	public Item getItem(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				items.remove(item);
				return item;
			}
		}
		return null;
	}

}
