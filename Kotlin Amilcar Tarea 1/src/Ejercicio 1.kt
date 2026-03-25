fun main() {

    var opcion: Int

    do {
        println("\n=== SISTEMA DE IMC ===")
        println("1. Calcular IMC")
        println("2. Salir")
        print("Seleccione una opción: ")

        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> calcularIMC()
            2 -> println("Saliendo del sistema...")
            else -> println("Opción inválida")
        }

    } while (opcion != 2)
}

fun calcularIMC() {

    println("\n--- Cálculo de IMC ---")

    val peso = leerDouble("Ingrese su peso (kg): ")
    val altura = leerDouble("Ingrese su altura (metros): ")

    val imc = peso / (altura * altura)

    val categoria = when {
        imc < 18.5 -> "Bajo peso"
        imc < 25.0 -> "Normal"
        imc < 30.0 -> "Sobrepeso"
        else -> "Obesidad"
    }

    println("\nResultado:")
    println("IMC: ${String.format("%.2f", imc)}")
    println("Categoría: $categoria")
}

fun leerDouble(mensaje: String): Double {
    var numero: Double?

    do {
        print(mensaje)
        numero = readLine()?.toDoubleOrNull()

        if (numero == null || numero <= 0) {
            println("Entrada inválida. Intente nuevamente.")
        }

    } while (numero == null || numero <= 0)

    return numero
}