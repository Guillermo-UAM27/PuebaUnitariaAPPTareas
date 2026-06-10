package ni.edu.uam.gesiondetareas.models

class GestorTareas {
    private val _listaTareas = mutableListOf<Tarea>()
    val listaTareas: List<Tarea> get() = _listaTareas

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

    fun obtenerTareasOrdenadasAlfabeticamente(): List<Tarea> = _listaTareas.sortedBy { it.titulo.lowercase() }

    fun obtenerPorcentajeCompletadas(): Float {
        if (_listaTareas.isEmpty()) return 0f
        val completadas = _listaTareas.count { it.esCompletada }
        return (completadas.toFloat() / _listaTareas.size) * 100f
    }
}
