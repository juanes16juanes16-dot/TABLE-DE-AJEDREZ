// router.js - Gestión de rutas y navegación SPA

/**
 * Función principal del router.
 * Lee el hash actual de la URL y ejecuta la función de renderizado correspondiente.
 * Si el hash no existe o no se reconoce, muestra la tienda por defecto.
 */
function navegar() {
    // Definir las rutas aquí dentro para que las funciones ya estén disponibles
    // (components.js se carga antes que app.js, que es donde se llama a navegar())
    const rutas = {
        "#tienda": renderizarTienda,
        "#inventario": renderizarInventario,
        "#login": renderizarLogin,
        "#registro": renderizarRegistro
    };

    // Leer el hash actual de la URL (ejemplo: "#tienda")
    const hash = window.location.hash;

    // Buscar la función que corresponde a ese hash
    const funcionRuta = rutas[hash];

    // Si encontrar la función, la ejecutamos; si no, mostramos la tienda
    if (funcionRuta) {
        funcionRuta();
    } else {
        renderizarTienda();
    }

    // Aplicar la animación de transición al contenedor principal
    const contenedor = document.getElementById("app");
    aplicarAnimacion(contenedor);
}

/**
 * Aplica una animación de transición al contenedor recibido.
 * Lee el tipo de animación desde la configuración YAML cargada.
 * Añade una clase CSS de animación y la retira cuando termina.
 */
function aplicarAnimacion(contenedor) {
    // Determinar el tipo de animación desde la configuración
    // Si no hay configuración cargada, usamos "fade" por defecto
    let tipoAnimacion = "fade";

    if (configuracion.animations && configuracion.animations.type) {
        tipoAnimacion = configuracion.animations.type;
    }

    // Construir el nombre de la clase CSS (ejemplo: "fade-in", "slide-in")
    const claseAnimacion = tipoAnimacion + "-in";

    // Eliminar la clase por si ya existía (para reiniciar la animación)
    contenedor.classList.remove(claseAnimacion);

    // Forzar un reflow para que el navegador detecte el cambio
    void contenedor.offsetWidth;

    // Añadir la clase de animación al contenedor
    contenedor.classList.add(claseAnimacion);

    // Cuando la animación termine, retirar la clase para poder reutilizarla
    contenedor.addEventListener("animationend", function () {
        contenedor.classList.remove(claseAnimacion);
    }, { once: true });
}

// Escuchar el evento "hashchange" para navegar cada vez que cambie el hash
window.addEventListener("hashchange", navegar);
