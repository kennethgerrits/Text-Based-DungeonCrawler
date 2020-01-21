import java.util.ArrayList;

public class Player {
	// creates a room which holds the currentroom.
	private Room currentRoom;

	// creates the backpack.
	private ArrayList<Item> backpack;

	// this is the constructor, before the player gets created it already has a few
	// things: a backpack and a starting room.
	public Player(Room room) {
		backpack = new ArrayList<>();
		currentRoom = room;
	}

	// Handles the movement of the player.
	public boolean move(String direction) {
		Room nextRoom = currentRoom.openDoor(direction);
		if (nextRoom != null) {
			currentRoom = nextRoom;
			currentRoom.roomInformation();

			// index 3, room 4.
			if (currentRoom.getRoomNumber() == 3) {
				System.out.println("WOAH WATCH OUT, this room is cursed! You've been randomly teleported.");
				currentRoom = currentRoom.getRandomNearRoom();
				currentRoom.roomInformation();
			}
			return true;
		}
		return false;
	}

	// the player will look in the current room.
	public void look() {
		currentRoom.look();
	}

	// the player will be given the room information like the title and description
	// once the player enters a new room.
	public void roomEntranceInformation() {
		currentRoom.roomInformation();
	}

	// adds the pickup item to your backpack.
	public void pickUpItem(String itemName) {
		Item item = currentRoom.getItem(itemName);
		if (item != null) {
			backpack.add(item);
			showInventory();
		} else {
			System.out.println("Failed to pick up the item.");
		}
	}

	// removes an enemy from the room.
	public void killEnemyInRoom(String enemyName) {
		currentRoom.killEnemy(enemyName);
	}

	public void lootEnemyInRoom(String enemyName) {
		currentRoom.lootEnemy(enemyName);
	}

	// prints the items you've got in your backpack.
	public void showInventory() {
		System.out.println("You're currently holding the following items:");
		backpack.forEach(i -> System.out.println(i.getName()));
	}

	// checks if the item can be used, if so print the useage text and remove the
	// item.
	public void useItem(String itemName) {
		Item currentItem = hasItem(itemName);
		// if the item is in your backpack.
		if (currentItem != null) {
			printUseItem(currentItem);
			backpack.remove(currentItem);
		}
		// if the item is in the room.
		else {
			currentItem = currentRoom.getItem(itemName);
			if (currentItem != null) {
				printUseItem(currentItem);
			}
		}
	}

	// prints the useage text based on to the item name.
	private void printUseItem(Item item) {
		System.out.println(item.getUseageText());
	}

	// checks if an item can be dropped, if so, drop it in the current room.
	public void dropItem(String itemName) {
		Item currentItem = hasItem(itemName);
		if (currentItem != null) {
			backpack.remove(currentItem);
			currentRoom.addItem(currentItem);
			System.out.println("You've dropped [" + itemName + "]");
		}
	}

	// checks if the item is in the backpack, if so return it, else return null.
	public Item hasItem(String itemName) {
		for (Item item : backpack) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	// checks if a player is in the starting room, this is part of the
	// wincondition.
	public boolean isInStartRoom() {
		return currentRoom.getRoomNumber() == 0;
	}

	// checks if a player has the crown in his backpack, this is part of the
	// wincondition.
	public boolean playerHasCrown() {
		for (Item item : backpack) {
			if (item.getName().equals("Crown")) {
				return true;
			}
		}
		return false;
	}

	// prints the information once a player types 'help' in the console.
	public void showHelp() {
		System.out.println("Don't worry, we've got you covered! You can use the following commands:");
		System.out.println("Use the 'go' command to move, example: go South");
		System.out.println("Use the 'get' command to pick up an item in the room, example: get Torch");
		System.out.println("Use the 'drop' command to drop an item you have in your backpack, example: drop Torch");
		System.out.println("Use the 'use' command to use an item, example: use Torch");
		System.out.println("Use the 'pack' command to see what items you have stored in your backpack, example: pack");
		System.out.println("Use the 'help' command to see this message.. a bit ironic, isn't it? Example: help");
		System.out.println(
				"Use the 'look' command to look in a room, this will show you everything you need to know about that room, example: look");
		System.out
				.println("Use the 'fight' command to kill an enemy (if there's one in the room), example: kill Mimic");
		System.out.println(
				"Use the 'loot' command to loot a dead enemy (if there's one in the room), example: loot Mimic");
		System.out.println("Use the 'quit' command to quit at any time, example: quit");
	}
}
