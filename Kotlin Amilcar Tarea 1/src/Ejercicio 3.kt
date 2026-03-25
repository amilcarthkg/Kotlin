data class Estudiante(
    val nombre: String,
    val grado: String,
    val notas: List<Double>
) {
    val promedio: Double
        get() = notas.average()
}

fun main() {

    val estudiantes = mutableListOf<Estudiante>()

    println("=== REGISTRO DE ESTUDIANTES ===")

    print("¿Cuántos estudiantes deseas ingresar?: ")
    val cantidad = readLine()!!.toInt()

    for (i in 1..cantidad) {
        println("\nEstudiante #$i")

        print("Nombre: ")
        val nombre = readLine()!!

        print("Grado: ")
        val grado = readLine()!!

        print("Ingrese notas separadas por coma (ej: 80,75,90): ")
        val notasInput = readLine()!!

        val notas = notasInput.split(",").map { it.trim().toDouble() }

        estudiantes.add(Estudiante(nombre, grado, notas))
    }

    // Procesamiento
    val aprobados = estudiantes.filter { it.promedio >= 70.0 }
    val ordenados = aprobados.sortedByDescending { it.promedio }
    val top3 = ordenados.take(3)

    // Resultados
    println("\n=== TOP 3 ESTUDIANTES ===")
    if (top3.isEmpty()) {
        println("No hay estudiantes con promedio >= 70")
    } else {
        top3.forEach {
            println("${it.nombre} - Promedio: %.1f".format(it.promedio))
        }
    }
}