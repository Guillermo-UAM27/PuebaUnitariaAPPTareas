package ni.edu.uam.gesiondetareas.models

import java.text.Collator
import java.util.Locale

class GestorTareas {
    private val _listaTareas = mutableListOf<Tarea>()
    val listaTareas: List<Tarea> get() = _listaTareas.toList()

    private var contadorId = 1

    fun agregarTarea(titulo: String, descripcion: String = ""): Tarea? {
        if (titulo.isBlank()) return null
        val nuevaTarea = Tarea(id = contadorId++, titulo = titulo, descripcion = descripcion)
        _listaTareas.add(nuevaTarea)
        return nuevaTarea
    }

    fun eliminarTarea(id: Int): Boolean {
        return _listaTareas.removeIf { it.id == id }
    }

    fun marcarComoCompletada(id: Int) {
        val indice = _listaTareas.indexOfFirst { it.id == id }
        if (indice != -1) {
            val tareaActual = _listaTareas[indice]
            _listaTareas[indice] = tareaActual.copy(esCompletada = !tareaActual.esCompletada)
        }
    }

    fun obtenerTareasPendientes(): List<Tarea> = _listaTareas.filter { !it.esCompletada }
    
    fun obtenerTareasCompletadas(): List<Tarea> = _listaTareas.filter { it.esCompletada }

    fun contarTareasPendientes(): Int = _listaTareas.count { !it.esCompletada }

    fun obtenerTareasOrdenadasAlfabeticamente(): List<Tarea> {
        val collator = Collator.getInstance(Locale.forLanguageTag("es"))
        collator.strength = Collator.PRIMARY
        return _listaTareas.sortedWith { t1, t2 -> collator.compare(t1.titulo, t2.titulo) }
    }

    fun obtenerPorcentajeCompletadas(): Float {
        if (_listaTareas.isEmpty()) return 0f
        val completadas = _listaTareas.count { it.esCompletada }
        return (completadas.toFloat() / _listaTareas.size) * 100f
    }
}
