package ni.edu.uam.gesiondetareas

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class PantallaTareasUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_flujoCompletoDeUI() {
        // Lanzamos la pantalla de la App
        composeTestRule.setContent {
            PantallaTareas()
        }

        // 1. Campo de entrada acepta texto correctamente y el botón responde al click
        composeTestRule.onNodeWithTag("input_tarea").performTextInput("Aprender Espresso")
        composeTestRule.onNodeWithTag("btn_agregar").performClick()

        // 2. Agregar tarea: La tarea aparece en pantalla
        composeTestRule.onNodeWithText("Aprender Espresso").assertExists()

        // 3. Mostrar pendientes: La cantidad es correcta
        // El formato en la UI es "X tareas por completar"
        composeTestRule.onNodeWithTag("txt_pendientes").assertTextEquals("1 tareas por completar")

        // 4. Eliminar tarea: La tarea desaparece de la lista
        // Como es el ID 1, el tag dinámico del botón es btn_eliminar_1
        composeTestRule.onNodeWithTag("btn_eliminar_1").performClick()
        composeTestRule.onNodeWithText("Aprender Espresso").assertDoesNotExist()
        composeTestRule.onNodeWithTag("txt_pendientes").assertTextEquals("0 tareas por completar")
    }
}
