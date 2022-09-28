import java.rmi.registry.LocateRegistry

fun main() {
	val server = LocateRegistry
		.getRegistry(1069)
		.lookup(RemoteEquationSolver::class.simpleName)
	println(
		(server as IRemoteEquationSolver).solveSquareEquation(
			2.0, 3.0, -5.0
		)
	)
}