# 📋 Aplicación de Gestión de Tareas (GesionDeTareas)

Esta es una aplicación Android moderna desarrollada para la gestión eficiente de tareas pendientes, cumpliendo con los requerimientos de la asignatura de Programación Orientada a Objetos II. La aplicación utiliza **Kotlin** y **Jetpack Compose** para ofrecer una interfaz fluida, intuitiva y robusta.

---

## 🚀 Funcionalidades Principales (Parte I)

### 1. Modelo de Datos (`Tarea`)
Cada tarea cuenta con:
- **ID:** Identificador único autoincremental.
- **Título:** Nombre de la tarea.
- **Descripción:** Detalles adicionales (opcional).
- **Estado:** Indicador de si la tarea está *Pendiente* o *Completada*.

### 2. Gestión de Lógica (`GestorTareas`)
La aplicación permite realizar las siguientes operaciones:
- **Registrar tareas:** Añadir nuevos elementos a la lista.
- **Eliminar tareas:** Quitar tareas existentes.
- **Marcar como completada:** Cambiar el estado de pendiente a completado (y viceversa).
- **Contadores dinámicos:** Cálculo en tiempo real de tareas pendientes.

### 3. Interfaz de Usuario (UI)
Diseñada con **Jetpack Compose** y **Material Design 3**, incluye:
- **Dashboard de Progreso:** Una tarjeta visual que muestra el porcentaje de tareas finalizadas mediante una barra de progreso circular y lineal.
- **Campo de Entrada Validado:** No permite agregar tareas vacías y muestra mensajes de error intuitivos.
- **Listado Dinámico:** Uso de `LazyColumn` para un desplazamiento suave.
- **Acciones Rápidas:** Botones dedicados para eliminar y checkboxes para completar.

---

## 🧪 Pruebas Automatizadas

### Pruebas Unitarias (Parte II)
Implementadas con **JUnit 4**, validan la lógica de negocio en `GestorTareasTest.kt`:
- **Agregar:** Verifica el incremento de la lista.
- **Eliminar:** Garantiza que la tarea desaparezca.
- **Completar:** Asegura el cambio de estado.
- **Conteo:** Valora si el retorno de pendientes es exacto.

### Pruebas de Interfaz (Parte III)
Implementadas en `PantallaTareasUITest.kt`:
- Validación de entrada de texto.
- Verificación de que la tarea aparece en la lista tras hacer clic.
- Sincronización del contador de pendientes con la UI.

### Prueba Negativa (Parte IV)
Diseñada en la Actividad 6 para fallar intencionalmente al comparar el porcentaje de una lista vacía con 100%. Esto garantiza la integridad de los resultados lógicos.

---

## ✨ Retos Adicionales (Parte V)

Se implementaron las siguientes mejoras para una mejor experiencia de usuario:
- **Filtros Avanzados:** Botones (Chips) para filtrar por *Todas*, *Pendientes* y *Completadas*.
- **Orden Alfabetico:** Opción para ordenar las tareas de la A a la Z.
- **Indicador de Porcentaje:** Visualización exacta del progreso general del día.
- **Tematización Moderna:** Paleta de colores "Indigo & Emerald" para un diseño limpio y profesional.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose (Material 3)
- **Arquitectura:** Patrón de diseño basado en Gestores (State Management con `remember` y `mutableStateOf`).
- **Pruebas:** JUnit 4 & Compose Test Library.

---

## 📦 Instalación y Ejecución

1. Clonar o descargar el proyecto.
2. Abrir en **Android Studio (Ladybug o superior)**.
3. Sincronizar con Gradle.
4. Ejecutar en un Emulador o Dispositivo Físico (API 24+).
5. Para las pruebas: Click derecho en la carpeta `test` o `androidTest` y seleccionar **"Run 'Tests in...'"**.

---
**Desarrollado por:** [Tu Nombre]  
**Asignatura:** Programación Orientada a Objetos II  
**Universidad:** UAM
