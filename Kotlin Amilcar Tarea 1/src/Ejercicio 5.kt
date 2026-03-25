import java.security.interfaces.DSAParams

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    var stock: Int
)

sealed class OpInventario {
    data class Exito(val datos: Any) : OpInventario()
    data class Error(val mensaje: String) : OpInventario()
    object Sinstock : OpInventario()
}

class Inventario {
    private val productos = mutableListOf<Producto>()

    fun agregarProductos(producto: Producto): OpInventario {
        val existe = productos.any { it.id == producto.id }
        return if (existe) {
            OpInventario.Error("Ya existe un id ${producto.id}")
        } else {
            productos.add(producto)
            OpInventario.Exito("Producto agregado: ${producto.nombre}")
        }
    }

    fun buscarPorId(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun actualizarstock(id: Int, nuevoStock: Int): OpInventario {
        val producto = buscarPorId(id)

        return when {
            producto == null -> OpInventario.Error("No se encuentra el id del producto $id")
            nuevoStock < 0 -> OpInventario.Error("El stock no puede ser negativo")
            nuevoStock == 0 -> {
                producto.stock = nuevoStock
                OpInventario.Sinstock
            }
            else -> {
                producto.stock= nuevoStock
                OpInventario.Exito("Stock actualizado: ${producto.nombre} a $nuevoStock")
            }
        }
    }

    fun listarDisponible(): List<Producto>{
        return productos.filter { it.stock > 0 }
    }

    fun obtenerTodos(): List<Producto> {
        return productos
    }
}

fun List<Producto>.valorTotal(): Double {
    return this.sumOf { it.precio * it.stock }
}

fun List<Producto>.buscarPorNombre(query: String): List<Producto> {
    return this.filter { it.nombre.contains(query, ignoreCase = true) }
}

fun main() {
    val inventario = Inventario()

    println("Agregar producto")
    println(inventario.agregarProductos(Producto(1, "PC Gamer", 35000.0, 1)))
    println(inventario.agregarProductos(Producto(2, "Combo taladro y pisto de impacto milwaukee", 3000.0, 4)))
    println(inventario.agregarProductos(Producto(3, "Toromax", 1000.0, 18)))
    println(inventario.agregarProductos(Producto(4, "Monitor Sceptre", 1600.0, 10)))
    println(inventario.agregarProductos(Producto(5, "pan bimbo", 20.0, 0)))

    println("\nBusqueda por ID del producto")
    val productoBuscado = inventario.buscarPorId(3)
    if (productoBuscado != null) {
        println("El producto fue encontrado: $productoBuscado")
    } else {
        println("El producto no fue encontrado")
    }

    println("\nActualizado de stock")
    println(inventario.actualizarstock(5, 30))
    println(inventario.actualizarstock(2, 0))

    println("\nLista de productos disponibles")
    inventario.listarDisponible().forEach {
        println("ID: ${it.id}, Nombre: ${it.nombre}, Precio: ${it.precio}, Stock: ${it.stock}")
    }

    println("\nBusqueda por nombre de producto")
    val resultados = inventario.obtenerTodos().buscarPorNombre("te")
    resultados.forEach {
        println(it)
    }

    println("\nValor de todo el producto en invetario")
    val total = inventario.obtenerTodos().valorTotal()
    println("Valor total: Q$total")
}
//println("apenas me dio tiempo kotlin es mejor pero igual odio java")\\