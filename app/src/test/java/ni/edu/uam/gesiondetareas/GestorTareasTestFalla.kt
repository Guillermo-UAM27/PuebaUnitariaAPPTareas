package ni.edu.uam.gesiondetareas

import ni.edu.uam.gesiondetareas.models.GestorTareas
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Parte IV. Prueba Negativa (Actividad 6)
 * 
 * Este archivo contiene una prueba que falla intencionalmente para demostrar 
 * el manejo de errores lógicos y validación de requerimientos.
 * 
 * EXPLICACIÓN (Documento de la Actividad 6):
 * 1. ¿Por qué falla la prueba?: 
 *    La prueba intenta verificar que el porcentaje de tareas completadas es del 100% 
 *    cuando la lista de tareas está totalmente vacía. El GestorTareas devuelve 0.0f 
 *    por defecto cuando no hay elementos para evitar divisiones por cero o datos erróneos.
 * 
 * 2. Cuál es el resultado esperado (de la aserción):
 *    Se espera que el test falle con un 'AssertionError', indicando que se esperaba 100.0 
 *    pero el valor obtenido fue 0.0.
 * 
 * 3. Cómo corregir el error:
 *    Para que la prueba pase, se debe cambiar el valor esperado a 0.0f en el assertEquals, 
 *    ya que es el resultado lógico correcto para una lista sin elementos.
 */
class GestorTareasTestFalla {

    private lateinit var gestor: GestorTareas

    @Before
    fun setUp() {
        gestor = GestorTareas()
    }

    @Test
    fun testPorcentajeCompletadas_ListaVacia_FallaIntencionalmente() {
        val porcentajeEsperadoErroneo = 100f
        val porcentajeActual = gestor.obtenerPorcentajeCompletadas()

        // Esta línea provocará el fallo intencional solicitado
        assertEquals(
            "La prueba falla: El porcentaje en lista vacía debe ser 0, no 100",
            porcentajeEsperadoErroneo,
            porcentajeActual
        )
    }
}
