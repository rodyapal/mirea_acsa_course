import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import kotlin.random.Random
import java.awt.Color
import java.awt.Dimension
import java.awt.EventQueue
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JTextArea
import javax.swing.JTextField

fun client() {
	val clientId = Random.nextInt(0, 1000).toString()
	val socket = Socket("localhost", 50_001)
	val writer = PrintWriter(socket.getOutputStream())
	val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
	val panel = JPanel()

	val clientInputField = JTextField()
	clientInputField.preferredSize = Dimension(100, 25)
	panel.add(clientInputField)

	val b = JButton("Send")
	b.addActionListener {
		writer.println("$clientId :: " + clientInputField.text)
		writer.flush()
		clientInputField.text = ""
	}
	panel.add(b)

	val serverResponseArea = JTextArea().apply {
		preferredSize = Dimension(200, 500)
		isEnabled = false
		lineWrap = true
		disabledTextColor = Color.BLACK
	}
	Thread {
		while (true) if (reader.ready()) {
			val response = reader.readLine()
			serverResponseArea.append(response)
			serverResponseArea.append("\n")
			println(response)
		}
	}.start()
	panel.add(serverResponseArea)

	val frame = JFrame(clientId).apply {
		add(panel)
		pack()
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setLocationRelativeTo(null)
	}
	frame.addWindowListener(object : WindowListener {
		override fun windowClosed(p0: WindowEvent?) {
			writer.close(); reader.close(); socket.close()
		}

		override fun windowOpened(p0: WindowEvent?) {}
		override fun windowClosing(p0: WindowEvent?) {}
		override fun windowIconified(p0: WindowEvent?) {}
		override fun windowDeiconified(p0: WindowEvent?) {}
		override fun windowActivated(p0: WindowEvent?) {}
		override fun windowDeactivated(p0: WindowEvent?) {}
	})
	EventQueue.invokeLater { frame.isVisible = true }
}