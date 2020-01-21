
public class Enemy {
	// an enemy needs to be identified by its name.
	private String name;

	// an enemy needs to either be dead or alive.
	private String status;

	// this construcor sets the basic properties for each enemy.
	public Enemy(String name) {
		this.name = name;
		status = "dangerous";
	}

	// returns the enemy's name
	public String getName() {
		return name;
	}

	// returns the enemy's status.
	public String getStatus() {
		return status;
	}

	// sets the enemy's status.
	public void setStatus(String status) {
		this.status = status;
	}
}
