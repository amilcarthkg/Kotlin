// Funciones de una sola línea
fun celsiusAFahrenheit(c: Double): Double = (c * 9/5) + 32

fun fahrenheitACelsius(f: Double): Double = (f - 32) * 5/9

fun celsiusAKelvin(c: Double): Double = c + 273.15

// Función principal
fun convertir(valor: Double, desde: String, hasta: String): Double {
    return when {
        desde == "C" && hasta == "F" -> celsiusAFahrenheit(valor)
        desde == "F" && hasta == "C" -> fahrenheitACelsius(valor)
        desde == "C" && hasta == "K" -> celsiusAKelvin(valor)
        else -> {
            println("Conversión no válida")
            Double.NaN
        }
    }
}

fun main() {

    println("=== CONVERSOR DE TEMPERATURA ===")

    print("Ingrese el valor: ")
    val valor = readLine()?.toDoubleOrNull()

    if (valor == null) {
        println("Valor inválido")
        return
    }

    print("Desde (C/F/K): ")
    val desde = readLine()?.uppercase()

    print("Hasta (C/F/K): ")
    val hasta = readLine()?.uppercase()

    if (desde == null || hasta == null) {
        println("Unidad inválida")
        return
    }

    val resultado = convertir(valor, desde, hasta)

    if (!resultado.isNaN()) {
        println("Resultado: %.2f°$hasta".format(resultado))
    }
}