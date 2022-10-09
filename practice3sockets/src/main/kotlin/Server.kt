import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.util.Scanner
import java.util.Timer
import java.util.TimerTask
import java.util.Vector
import java.util.concurrent.Executors

private class MessageGetTask(
	private val socket: Socket,
	private val onMessageReceived: (message: String) -> Unit
) : Runnable {
	override fun run() {
		try {
			val input = Scanner(socket.getInputStream()).nextLine()
			println("Received: $input")
			onMessageReceived(input)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}

private class MessageBroadcastTask(
	private val broadcastData: Vector<String>,
	private val sockets: List<Socket>,
	private val onBroadcastComplete: (successful: Boolean) -> Unit
) : TimerTask() {
	override fun run() {
		print("Broadcast to ${sockets.size} sockets...")
		if (broadcastData.isEmpty() || sockets.isEmpty()) {
			println(" unneeded")
			onBroadcastComplete(false)
			return
		}
		try {
			sockets.forEach { socket ->
				val writer = PrintWriter(socket.getOutputStream())
				val message = broadcastData
					.elements()
					.toList()
					.joinToString(
						separator = "\n"
					)
				writer.println(message)
			}
		} catch (e: Exception) {
			println(" failed\n${e.message}")
			onBroadcastComplete(false)
			return
		}
		println(" done")
		onBroadcastComplete(true)
	}
}

fun main() {
	val listener = ServerSocket(50_001)
	val sockets = mutableListOf<Socket>()
	val data = Vector<String>()
	data.addElement("sample string")
	val pool = Executors.newFixedThreadPool(20)
	val broadcastTimer = Timer().scheduleAtFixedRate(
		MessageBroadcastTask(data, sockets) { successful ->
			if (successful) {
				data.clear()
			}
		}, 5000, 10_000
	)
	while (true) {
		pool.execute(
			MessageGetTask(
				listener.accept().also { sockets.add(it) }
			) { message ->
				data.addElement(message)
			}
		)
	}
}