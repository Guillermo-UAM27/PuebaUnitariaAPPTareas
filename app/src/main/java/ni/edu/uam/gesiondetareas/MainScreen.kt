package ni.edu.uam.gesiondetareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ni.edu.uam.gesiondetareas.models.GestorTareas
import ni.edu.uam.gesiondetareas.models.Tarea

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TareaAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaTareas()
                }
            }
        }
    }
}

@Composable
fun TareaAppTheme(content: @Composable () -> Unit) {
    val lightColors = lightColorScheme(
        primary = Color(0xFF6366F1), // Indigo moderno
        onPrimary = Color.White,
        primaryContainer = Color(0xFFE0E7FF),
        secondary = Color(0xFF10B981), // Esmeralda para estados positivos
        onSecondary = Color.White,
        background = Color(0xFFF8FAFC),
        surface = Color.White,
        error = Color(0xFFEF4444)
    )
    MaterialTheme(colorScheme = lightColors, content = content)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaTareas(gestor: GestorTareas = remember { GestorTareas() }) {
    var textoTarea by remember { mutableStateOf("") }
    var errorValidacion by remember { mutableStateOf<String?>(null) }
    var listaMutada by remember { mutableStateOf(0) }
    var filtroSeleccionado by remember { mutableStateOf("Todas") }

    val tareasAMostrar = remember(listaMutada, filtroSeleccionado) {
        when (filtroSeleccionado) {
            "Pendientes" -> gestor.obtenerTareasPendientes()
            "Completadas" -> gestor.obtenerTareasCompletadas()
            "Ordenadas" -> gestor.obtenerTareasOrdenadasAlfabeticamente()
            else -> gestor.listaTareas
        }
    }

    val progreso = gestor.obtenerPorcentajeCompletadas() / 100f

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.AutoMirrored.Filled.List, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.width(8.dp))
                        Text("Mis Pendientes", fontWeight = FontWeight.Black)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Panel de Progreso Visual
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Tu progreso", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text("${(progreso * 100).toInt()}%", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
                    }
                    Spacer(Modifier.height(10.dp))
                    LinearProgressIndicator(
                        progress = { progreso },
                        modifier = Modifier.fillMaxWidth().height(12.dp),
                        strokeCap = StrokeCap.Round,
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = Color.White.copy(alpha = 0.5f)
                    )
                    Text(
                        text = "${gestor.contarTareasPendientes()} tareas por completar",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp).testTag("txt_pendientes")
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Entrada de Tareas con validación
            OutlinedTextField(
                value = textoTarea,
                onValueChange = {
                    textoTarea = it
                    if (it.isNotBlank()) errorValidacion = null
                },
                label = { Text("¿Qué sigue en tu lista?") },
                placeholder = { Text("Ej. Estudiar para el examen...") },
                modifier = Modifier.fillMaxWidth().testTag("input_tarea"),
                shape = RoundedCornerShape(16.dp),
                isError = errorValidacion != null,
                supportingText = { if (errorValidacion != null) Text(errorValidacion!!) },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (textoTarea.isBlank()) {
                                errorValidacion = "¡Escribe algo primero!"
                            } else {
                                gestor.agregarTarea(textoTarea)
                                textoTarea = ""
                                errorValidacion = null
                                listaMutada++
                            }
                        },
                        modifier = Modifier.testTag("btn_agregar")
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Chips de Filtros
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                val opciones = listOf("Todas", "Pendientes", "Completadas", "Ordenadas")
                opciones.forEach { opcion ->
                    FilterChip(
                        selected = filtroSeleccionado == opcion,
                        onClick = { filtroSeleccionado = opcion },
                        label = { Text(opcion) },
                        modifier = Modifier.testTag("btn_filtro_$opcion"),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista Animada
            if (tareasAMostrar.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.CheckCircle, null, modifier = Modifier.size(60.dp), tint = Color.LightGray)
                        Text("No hay tareas aquí", color = Color.Gray)
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().testTag("lista_tareas"),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(tareasAMostrar, key = { it.id }) { tarea ->
                        ItemTareaModerna(
                            tarea = tarea,
                            onToggle = { gestor.marcarComoCompletada(tarea.id); listaMutada++ },
                            onDelete = { gestor.eliminarTarea(tarea.id); listaMutada++ }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemTareaModerna(tarea: Tarea, onToggle: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (tarea.esCompletada) Color(0xFFF0FDF4) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = tarea.esCompletada,
                onCheckedChange = { onToggle() },
                modifier = Modifier.testTag("check_${tarea.id}")
            )
            Text(
                text = tarea.titulo,
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp).testTag("item_titulo_${tarea.titulo}"),
                style = MaterialTheme.typography.bodyLarge.copy(
                    textDecoration = if (tarea.esCompletada) TextDecoration.LineThrough else null,
                    color = if (tarea.esCompletada) Color.Gray else Color.Black
                )
            )
            IconButton(
                onClick = onDelete,
                modifier = Modifier.testTag("btn_eliminar_${tarea.id}")
            ) {
                Icon(Icons.Default.Delete, "Eliminar", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}