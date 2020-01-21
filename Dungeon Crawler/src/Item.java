
public class Item {
	// an item needs a name.
	private String name;

	// an item needs an usage text.
	private String useageText;

	// this is the constuctor, the items will get an useageText accordingly to their
	// name.
	public Item(String name) {
		this.name = name;

		switch (name) {
		case "Torch":
			useageText = "You've lit the torch, everything seems so clear now...";
			break;

		case "Sword":
			useageText = "You slashed with your sword in front of you.";
			break;

		case "Crown":
			useageText = "You put the crown on your head, but then it disappears ... that was weird.";
			break;

		case "Food":
			useageText = "Just like my mums... delicious...";
			break;

		case "Water":
			useageText = "Ahh, some fresh water, I really needed that.";
			break;

		case "Coin":
			useageText = "A reward earned by defeating an enemy, and you just threw it away... What a waste..";
			break;

		default:
			useageText = "Error: no item.";
			break;
		}
	}

	// returns item name.
	public String getName() {
		return name;
	}

	// returns useage text.
	public String getUseageText() {
		return useageText;
	}

}
