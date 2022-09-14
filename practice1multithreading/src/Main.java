public class Main {
	public static void main(String[] args) {
		Game game = new Game();
		Thread ping = new Thread(new Player(game, Player.Role.PING));
		Thread pong = new Thread(new Player(game, Player.Role.PONG));
		ping.start();
		pong.start();
	}
}
