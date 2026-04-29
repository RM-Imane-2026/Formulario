# Formulario + BBDD Cloud (Supabase)

Aplicación Android desarrollada en Kotlin que implementa un formulario de registro con validaciones integrado con una base de datos en la nube (Supabase).

---

## Requisitos implementados:
- **Campos implementados**: Título, Descripción, Categoría, Prioridad(1-5) y Email.
- **Validaciones**:
    - Título (5-60 caracteres).
    - Descripción (20-500 caracteres).
    - Email (validación de formato **@**, **.com**, **.es**).
    - Prioridad (rango entre 1-5)
- **Estado del botón**: El botón de envío se deshabilita si los campos no cumplen los requisitos exigidos y cambia a "Enviando..." para evitar envíos duplicados (no volver a pulsar a guardar).

## Persistencia y Listado
- **Integrada la base Supabase**:
  - **Insercción**: Envío de datos a la tabla solicitudes.
  - **Listado**: Se muestra el listado de mis solicitudes con una LazyColumn ordenados por fecha de forma descendente.

## Arquitectura Técnica
- **MVVM**
- **Hilt/Dagger**

---
  
## Pendiente (Falta de tiempo)
- **Manejo de Errores y Estados de UI**: **No implementado por limitaciones de tiempo**.  Mi prioridad fue tener un código que funcione correctamente y que cubra los requisitos esenciales. Debido a imprevistos técnicos surgidos durante mi desarrollo (conflictos de dependencias y refactorización del código base), el tiempo disponible se me consumió resolviendo la estabilidad del proyecto. Como consecuencia, no he podido finalizar la gestión visual de estados (Carga, Éxito, Error) ni el botón de reintento manual. Mi intención era adaptar el sistema de gestión de estados y errores que ya tengo implementado en mi proyecto **Pokedex**, del cual planeaba sacar esta funcionalidad, pero no fue posible.

---

## Nota:
Para el desarrollo de este ejercicio, he aplicado conceptos a través de:
  - **Mis propios proyectos**: He reutilizado estructuras de arquitectura y navegación que he desarrollado anteriormente.
  - **Videos y Recursos Externos**: He consultado tutoriales para la parte de la base de datos (Supabase) y para algunas partes de mejorar la interfaz a modo visual.

---
## Configuración y Ejecución

1. **Crear cuenta en Supabase**: Registrarse y crear un nuevo proyecto.
2. **Crear la tabla 'solicitudes'**: Crear la tabla solicitudes y añadir los **RLS policy** (mínimo para el select y el inserte con todos 'anon').
3. **Obtener las credenciales**: Ir a Project Settings > API y copiar la 'Publishable key' de API Keys y Data API.
4. **Configurar el proyecto**: Copiar las credenciales anteriores en el proyecto en el fichero 'NetworkModule' de formulario/di.
5. **Ejecutar**: Sincronizar Gradle y ejecutar dandole al Play en el emulador o dispositivo físico.

---
## Demo [video](video/demo.mp4)

En mi proyecto, tengo adjuntado mi video demo mostrando las funcionalidad implementadas. Se puede visualizar que a la hora de poner **Modo avión**, al intentar crear una solicitud pulsando al botón **guardar**, directamente vuelve a la pantalla de inicio. Y al intentar ver la lista de mis solicitudes hechas, me muestra un mensaje de **"No hay solicitudes"**. 


  
