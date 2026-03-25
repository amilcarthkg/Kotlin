data class Contacto(
    val nombre: String,
    val telefono: String?,
    val email: String?
)

// Base de datos (mutable)
val contactos = mutableMapOf<String, Contacto?>(
    "1" to Contacto("Ana", "555-1234", "ana@email.com"),
    "2" to Contacto("Luis", null, "luis@email.com"),
    "3" to Contacto("Carlos", "555-5678", null),
    "4" to null
)

fun buscarContacto(id: String): Contacto? {
    return contactos[id]
}

fun agregarContacto() {
    println("\n--- Agregar Contacto ---")

    print("ID: ")
    val id = readLine()!!

    print("Nombre: ")
    val nombre = readLine()!!

    print("Teléfono (puede ir vacío): ")
    val telefonoInput = readLine()
    val telefono = if (telefonoInput.isNullOrBlank()) null else telefonoInput

    print("Email (puede ir vacío): ")
    val emailInput = readLine()
    val email = if (emailInput.isNullOrBlank()) null else emailInput

    contactos[id] = Contacto(nombre, telefono, email)

    println("Contacto agregado ✔")
}

fun buscar() {
    println("\n--- Buscar Contacto ---")

    print("Ingrese ID: ")
    val id = readLine()!!

    val contacto = buscarContacto(id)

    // null safety completo
    contacto?.let {

        println("\nContacto encontrado:")
        println("Nombre: ${it.nombre}")
        println("Teléfono: ${it.telefono ?: "No disponible"}")
        println("Email: ${it.email ?: "No disponible"}")

    } ?: println("Contacto no encontrado ❌")
}

fun eliminarContacto() {
    println("\n--- Eliminar Contacto ---")

    print("Ingrese ID: ")
    val id = readLine()!!

    val eliminado = contactos.remove(id)

    if (eliminado != null) {
        println("Contacto eliminado ✔")
    } else {
        println("No existe ese contacto ❌")
    }
}

fun mostrarContactos() {
    println("\n--- Lista de Contactos ---")

    if (contactos.isEmpty()) {
        println("No hay contactos")
        return
    }

    contactos.forEach { (id, contacto) ->
        print("ID: $id | ")

        contacto?.let {
            print("Nombre: ${it.nombre}, ")
            print("Tel: ${it.telefono ?: "No disponible"}, ")
            print("Email: ${it.email ?: "No disponible"}")
        } ?: print("Contacto vacío")

        println()
    }
}

fun main() {

    var opcion: Int

    do {
        println("\n=== SISTEMA DE CONTACTOS ===")
        println("1. Agregar contacto")
        println("2. Buscar contacto")
        println("3. Eliminar contacto")
        println("4. Mostrar todos")
        println("5. Salir")
        print("Seleccione una opción: ")

        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> agregarContacto()
            2 -> buscar()
            3 -> eliminarContacto()
            4 -> mostrarContactos()
            5 -> println("Saliendo del sistema...")
            else -> println("Opción inválida")
        }

    } while (opcion != 5)
}