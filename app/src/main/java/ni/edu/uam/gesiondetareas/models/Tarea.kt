package ni.edu.uam.gesiondetareas.models


data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String = "",
    val esCompletada: Boolean = false // Estado: true = Completada, false = Pendiente
)
