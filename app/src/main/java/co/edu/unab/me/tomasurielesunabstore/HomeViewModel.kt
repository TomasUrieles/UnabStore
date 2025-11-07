package co.edu.unab.me.tomasurielesunabstore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val repo = ProductoRepository()
    val productos = MutableLiveData<List<Producto>>(emptyList())

    fun cargarProductos() {
        repo.obtenerProductos(
            onSuccess = { lista -> productos.value = lista },
            onError = { _ -> productos.value = emptyList() }
        )
    }

    fun agregarProducto(nombre: String, descripcion: String, precio: Double) {
        val producto = Producto(descripcion = descripcion, nombre = nombre, precio = precio,)
        repo.agregarProducto(producto) { _, _ -> cargarProductos() }
    }

    fun eliminarProducto(id: String) {
        repo.eliminarProducto(id) { _, _ -> cargarProductos() }
    }
}
