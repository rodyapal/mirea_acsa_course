import java.rmi.Remote
import java.rmi.RemoteException
import java.rmi.registry.LocateRegistry
import java.rmi.server.UnicastRemoteObject
import kotlin.jvm.Throws
import kotlin.math.sqrt

interface IRemoteEquationSolver : Remote {
	@Throws(RemoteException::class)
	fun solveSquareEquation(a: Double, b: Double, c: Double): Pair<Double, Double>
}

class RemoteEquationSolver : IRemoteEquationSolver {
	override fun solveSquareEquation(a: Double, b: Double, c: Double): Pair<Double, Double> {
		val d = sqrt(b * b - 4 * a * c)
		val x1 = (-b + d) / (2 * a)
		val x2 = (-b - d) / (2 * a)
		println("[EQUATION_LOG]:\nd = $d\nx1 = $x1\nx2 = $x2")
		return x1 to x2
	}
}

fun main() {
	try {
		LocateRegistry.createRegistry(1069).bind(
			RemoteEquationSolver::class.simpleName,
			UnicastRemoteObject.exportObject(
				RemoteEquationSolver(), 0
			)
		)
	} catch (e: RemoteException) {
		e.printStackTrace()
	}
}