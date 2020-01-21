import java.util.Scanner;

public class Game {
	private Player player;
	// is needed to scan user input.
	Scanner sc = new Scanner(System.in);

	// this is the constructor, everything a game needs before it gets started will
	// be exacuted here.
	public Game() {
		// makes room for 10 rooms.
		Room[] rooms = new Room[10];

		// creates 10 new rooms.
		for (int i = 0; i < 10; i++) {
			rooms[i] = new Room(i);
		}

		// gives each room its properties: items, directions, enemies, description.
		rooms[0].addRoom("South", rooms[1]);
		rooms[0].setDescription("the entrance and the exit of the cave.");

		rooms[1].addRoom("North", rooms[0]);
		rooms[1].addRoom("East", rooms[2]);
		rooms[1].addRoom("South", rooms[4]);
		rooms[1].addItem(new Item("Torch"));
		rooms[1].setDescription("very dark.");
		rooms[1].addEnemy(new Enemy("Mimic"));

		rooms[2].addRoom("West", rooms[1]);
		rooms[2].addRoom("East", rooms[3]);
		rooms[2].addRoom("South", rooms[5]);
		rooms[2].setDescription("huge!");

		rooms[3].addRoom("South", rooms[6]);
		rooms[3].addRoom("West", rooms[2]);
		rooms[3].setDescription("looking like a slaughterhouse!");

		rooms[4].addRoom("North", rooms[1]);
		rooms[4].addRoom("East", rooms[5]);
		rooms[4].addRoom("South", rooms[8]);
		rooms[4].addItem(new Item("Food"));
		rooms[4].setDescription("looking like a place where people used to eat.");

		rooms[5].addRoom("North", rooms[2]);
		rooms[5].addRoom("East", rooms[6]);
		rooms[5].addRoom("South", rooms[9]);
		rooms[5].addRoom("West", rooms[4]);
		rooms[5].addItem(new Item("Sword"));
		rooms[5].setDescription("huge ... and it has so many other rooms connected too..");

		rooms[6].addRoom("North", rooms[3]);
		rooms[6].addRoom("East", rooms[7]);
		rooms[6].addRoom("West", rooms[5]);
		rooms[6].addItem(new Item("Water"));
		rooms[6].setDescription("so strange... how did the water get here?");

		rooms[7].addRoom("West", rooms[6]);
		rooms[7].setDescription("looking to be a dead end.");

		rooms[8].addRoom("North", rooms[4]);
		rooms[8].addRoom("East", rooms[9]);
		rooms[8].addItem(new Item("Crown"));
		rooms[8].setDescription("gorgeous! Someone very special must've been here.");
		rooms[8].addEnemy(new Enemy("Goblin"));

		rooms[9].addRoom("West", rooms[8]);
		rooms[9].setDescription("looking to be the end.");
		rooms[9].addEnemy(new Enemy("Giant"));

		// creates a player, which starts in the startingroom.
		player = new Player(rooms[0]);
	}

	// runs the game and ends it once needed.
	public void run() {
		player.roomEntranceInformation();
		boolean keepPlaying = true;
		while (keepPlaying) {
			// ends the game once the wincondition is true
			if (checkWinCondition()) {
				System.out.println("Congratulations, you've won!");
				System.out.println("Thanks for playing!");
				System.out.println("Made by Kenneth Gerrits (2125449) - PROG2 assessment");
				return;
			}
			// reads the user input and sends it to the handle command method once needed.
			String input = sc.nextLine();
			input = input.trim();
			if (input.equals("quit")) {
				keepPlaying = false;
			} else {
				handleCommand(input);
			}
		}
	}

	// checks if the player has the needed conditions in order to win.
	public boolean checkWinCondition() {
		return (player.isInStartRoom() && player.playerHasCrown());
	}

	// reads the user input, then seperates each word and does an action based on
	// the first (and second) word. First word would be inputWords[0], second word
	// would be inputWords[1] etc...
	private void handleCommand(String userInput) {
		// splits the user input by one or more whitespaces, hence the \\s+
		String[] inputWords = userInput.split("\\s+");

		try {
			switch (inputWords[0]) {
			case "go":
				player.move(inputWords[1]);
				break;

			case "get":
				player.pickUpItem(inputWords[1]);
				;
				break;

			case "drop":
				player.dropItem(inputWords[1]);
				break;

			case "use":
				player.useItem(inputWords[1]);
				;
				break;

			case "pack":
				player.showInventory();
				break;

			case "help":
				player.showHelp();
				break;

			case "look":
				player.look();
				break;

			case "fight":
				player.killEnemyInRoom(inputWords[1]);
				break;

			case "loot":
				player.lootEnemyInRoom(inputWords[1]);
				break;
			}
		} catch (Exception e) {
			System.out.println("Invalid command type 'help' to see all commands.");
		}
	}
}
