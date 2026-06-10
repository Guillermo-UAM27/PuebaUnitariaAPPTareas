package ni.edu.uam.gesiondetareas

import ni.edu.uam.gesiondetareas.models.GestorTareas
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GestorTareasTest {

    private lateinit var gestor: GestorTareas

    @Before
    fun setUp() {
        gestor = GestorTareas()
    }

    @Test
    fun agregarTarea_LaListaIncrementaEnUno() {
        gestor.agregarTarea("Estudiar Kotlin")
        assertEquals(1, gestor.listaTareas.size)
    }

    @Test
    fun eliminarTarea_LaTareaDesapareceDeLaLista() {
        val tarea = gestor.agregarTarea("Comprar Leche")
        assertNotNull(tarea)
        gestor.eliminarTarea(tarea!!.id)
        assertEquals(0, gestor.listaTareas.size)
    }

    @Test
    fun completarTarea_ElEstadoCambiaACompletada() {
        val tarea = gestor.agregarTarea("Hacer Ejercicio")
        assertNotNull(tarea)
        gestor.marcarComoCompletada(tarea!!.id)
        assertTrue(gestor.listaTareas.first { it.id == tarea.id }.esCompletada)
    }

    @Test
    fun contarTareasPendientes_RetornaElValorCorrecto() {
        gestor.agregarTarea("T1")
        val t2 = gestor.agregarTarea("T2")
        gestor.agregarTarea("T3")
        gestor.marcarComoCompletada(t2!!.id)
        
        // Deberían quedar 2 pendientes
        assertEquals(2, gestor.contarTareasPendientes())
    }

    @Test
    fun listaVacia_RetornaCeroPendientes() {
        assertEquals(0, gestor.contarTareasPendientes())
    }

    /**
     * ACTIVIDAD 6: Prueba Negativa
     * Esta prueba falla intencionalmente al intentar verificar que el porcentaje es 100% 
     * en una lista vacía.
     * 
     * Por qué falla: El método obtenerPorcentajeCompletadas() devuelve 0f cuando no hay tareas,
     * pero el test espera 100f.
     * Resultado esperado: La prueba falla (AssertionError).
     * Cómo corregir: Se debe cambiar el valor esperado a 0f en el assertEquals.
     */
    @Test
    fun testNegativo_FallaIntencionalmente() {
        val porcentaje = gestor.obtenerPorcentajeCompletadas()
        // Para que la prueba falle (Actividad 6), descomenta la siguiente línea:
        // assertEquals("Error esperado: Porcentaje no es 100 en lista vacía", 100f, porcentaje)
        
        // Validación correcta para asegurar que el build no se detenga por defecto:
        assertEquals(6, porcentaje)
    }
}
