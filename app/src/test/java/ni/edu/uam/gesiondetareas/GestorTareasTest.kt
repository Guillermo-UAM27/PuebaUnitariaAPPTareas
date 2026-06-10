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

    // --- Parte II. Pruebas Unitarias Obligatorias (Actividad 4) ---

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


    // --- Parte V. Actividad 7: Reto Adicional ---

    @Test
    fun obtenerTareasOrdenadasAlfabeticamente_RetornaListaCorrecta() {
        // Con la corrección del Collator, el orden será Árbol -> Barco -> Zebra
        gestor.agregarTarea("Zebra")
        gestor.agregarTarea("Árbol")
        gestor.agregarTarea("Barco")

        val listaOrdenada = gestor.obtenerTareasOrdenadasAlfabeticamente()

        assertEquals("Árbol", listaOrdenada[0].titulo)
        assertEquals("Barco", listaOrdenada[1].titulo)
        assertEquals("Zebra", listaOrdenada[2].titulo)
    }

    @Test
    fun obtenerPorcentajeCompletadas_CalculaValorExacto() {
        gestor.agregarTarea("T1")
        val t2 = gestor.agregarTarea("T2")
        gestor.agregarTarea("T3")
        gestor.agregarTarea("T4")

        gestor.marcarComoCompletada(t2!!.id) // 1 de 4 = 25%

        assertEquals(25f, gestor.obtenerPorcentajeCompletadas())
    }
}
