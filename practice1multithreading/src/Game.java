public class Game {
	private boolean isPing = true;

	public synchronized void ping() {
		while (!isPing) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread Interrupted");
			}
		}
		isPing = false;
		System.out.println("Ping");
		notifyAll();
	}

	public synchronized void pong() {
		while (isPing) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread Interrupted");
			}
		}
		isPing = true;
		System.out.println("Pong");
		notifyAll();
	}
}
