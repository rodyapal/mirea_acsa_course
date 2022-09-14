public class Player implements Runnable {
	private final Game game;
	private final Role role;

	public Player(Game game, Role role) {
		this.game = game;
		this.role = role;
	}

	public enum Role {
		PING, PONG
	}

	@Override
	public void run() {
		for (int i = 0; i < 12; i++) {
			if (role == Role.PING) {
				game.ping();
			} else {
				game.pong();
			}
		}
	}
}
