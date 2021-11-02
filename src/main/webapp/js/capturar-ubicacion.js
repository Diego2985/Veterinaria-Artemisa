const getUbicacion = document.getElementById("getUbicacion")
const setUbicacion = document.getElementById("setUbicacion")
const setMensaje = document.getElementById("setMensaje")

getUbicacion.addEventListener("click", () => {
    setMensaje.value = "Listo"
    setUbicacion.value = "-555.5,-555.5"
})