import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.Scanner
import kotlin.random.Random

class Client {
	private val messagePrefix = "[${Random.nextInt(0, Int.MAX_VALUE)}]:"
	private val socket = Socket("localhost", 50_001)
	private val reader = Scanner(socket.getInputStream())
	private val writer = PrintWriter(socket.getOutputStream())

	private val receiver = Thread {
		while (true) {
			if (reader.hasNext()) {
				println(
					"Received: ${reader.nextLine()}"
				)
			}
		}
	}

	private val sender = Thread {
		while (true) {
			val input = readln()
			writer.println(
				"$messagePrefix $input"
			)
			writer.flush()
		}
	}

	fun start() {
		receiver.start()
		sender.start()
	}
}