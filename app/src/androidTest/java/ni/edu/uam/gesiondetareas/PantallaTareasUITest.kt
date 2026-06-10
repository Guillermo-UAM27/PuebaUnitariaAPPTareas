package ni.edu.uam.gesiondetareas

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class PantallaTareasUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Actividad 5: Casos de Prueba de UI
     */

    @Test
    fun campoEntrada_aceptaTextoCorrectamente() {
        composeTestRule.setContent { PantallaTareas() }
        
        val textoPrueba = "Tarea de Prueba"
        composeTestRule.onNodeWithTag("input_tarea").performTextInput(textoPrueba)
        
        // Verificamos que el texto esté presente en el campo
        composeTestRule.onNodeWithTag("input_tarea").assertTextContains(textoPrueba)
    }

    @Test
    fun agregarTarea_apareceEnPantalla_y_botonRespondeAlClic() {
        composeTestRule.setContent { PantallaTareas() }

        val tituloTarea = "Nueva Tarea UI"
        
        // Campo de entrada acepta texto
        composeTestRule.onNodeWithTag("input_tarea").performTextInput(tituloTarea)
        
        // Botón Agregar responde al clic
        composeTestRule.onNodeWithTag("btn_agregar").performClick()

        // La tarea aparece en pantalla
        composeTestRule.onNodeWithText(tituloTarea).assertExists()
    }

    @Test
    fun eliminarTarea_desapareceDeLaLista() {
        composeTestRule.setContent { PantallaTareas() }

        // Agregamos una tarea primero
        val titulo = "Tarea a eliminar"
        composeTestRule.onNodeWithTag("input_tarea").performTextInput(titulo)
        composeTestRule.onNodeWithTag("btn_agregar").performClick()
        
        // Verificamos que existe
        composeTestRule.onNodeWithText(titulo).assertExists()

        // Eliminamos la tarea (asumiendo ID 1 para la primera tarea)
        composeTestRule.onNodeWithTag("btn_eliminar_1").performClick()

        // La tarea desaparece de la lista
        composeTestRule.onNodeWithText(titulo).assertDoesNotExist()
    }

    @Test
    fun mostrarPendientes_laCantidadEsCorrecta() {
        composeTestRule.setContent { PantallaTareas() }

        // Inicialmente 0
        composeTestRule.onNodeWithTag("txt_pendientes").assertTextEquals("0 tareas por completar")

        // Agregamos una
        composeTestRule.onNodeWithTag("input_tarea").performTextInput("Tarea 1")
        composeTestRule.onNodeWithTag("btn_agregar").performClick()

        // Verificamos cantidad 1
        composeTestRule.onNodeWithTag("txt_pendientes").assertTextEquals("1 tareas por completar")
        
        // Agregamos otra
        composeTestRule.onNodeWithTag("input_tarea").performTextInput("Tarea 2")
        composeTestRule.onNodeWithTag("btn_agregar").performClick()
        
        // Verificamos cantidad 2
        composeTestRule.onNodeWithTag("txt_pendientes").assertTextEquals("2 tareas por completar")
        
        // Completamos una para ver si baja el contador (opcional pero bueno para la lógica)
        composeTestRule.onNodeWithTag("check_1").performClick()
        composeTestRule.onNodeWithTag("txt_pendientes").assertTextEquals("1 tareas por completar")
    }
}
