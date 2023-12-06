# GameNexus

<style>
    .centrado {
	display: block;
        margin-left: auto;
        margin-right: auto;
    }
</style>



<p>Proyecto del ciclo formativo de Grado Superior en Desarrollo de Aplicaciones Multiplataforma (DAM) del centro CPIFP Los Enlaces, Zaragoza</p>

<p>GameNexus es una aplicación Android desarrollada en Kotlin utilizando Jetpack Compose y la biblioteca Retrofit para la comunicación con la API de RAWG, un popular sitio web de videojuegos.</p>
	
<p>A continuación, se detallan las tecnologías y componentes clave utilizados en el proyecto:</p>

<ul>

<li>Kotlin: El lenguaje de programación principal empleado para el desarrollo de la aplicación. Kotlin es conocido por su concisión y seguridad, lo que lo convierte en una elección sólida para el desarrollo de aplicaciones Android.</li>

<li>Jetpack Compose: Esta tecnología permite la creación de interfaces de usuario modernas y dinámicas utilizando un enfoque declarativo, lo que facilita la creación y mantenimiento de la interfaz de usuario.</li>

<li>Retrofit: La biblioteca Retrofit se utiliza para realizar solicitudes HTTP eficientes a la API de RAWG. Facilita la comunicación con la API y el manejo de datos JSON de manera sencilla.</li>

<li>API de RAWG: Proporciona información sobre videojuegos, incluyendo detalles del juego, reseñas, clasificaciones y más. Esta API es la fuente de datos central para GameApp y se utiliza para obtener información actualizada en tiempo real.</li>

<li>Arquitectura MVVM: Sigue el patrón de arquitectura MVVM (Model-View-ViewModel) para separar la lógica de la interfaz de usuario de la lógica de negocio y los datos. Esto mejora la modularidad y la facilidad de prueba del código.</li>

<li>Búsqueda Avanzada: Se implementa una funcionalidad de búsqueda avanzada que permite a los usuarios buscar juegos por género, plataforma, fecha de lanzamiento y otros criterios, lo que se logra a través de consultas a la API de RAWG.</li>

</ul>

<p class="centrado"><img src="/pictures/1.png" alt="picture1"></p>
<p class="centrado"><img src="/pictures/2.png" alt="picture2"></p>