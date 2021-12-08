const getUbicacion = document.getElementById("getUbicacion")
const setLatitud = document.getElementById("setLatitud")
const setLongitud = document.getElementById("setLongitud")
const setMensaje = document.getElementById("setMensaje")
const buttonContinuar = document.getElementById("continuar")

getUbicacion.addEventListener("click", () => {
    if (navigator.geolocation) {
        // navigator.geolocation.getCurrentPosition(localizacion => {
            latitud = -34.588902;
            longitud = -58.409851;
            setMensaje.value = "Listo"
            setLatitud.value = latitud
            setLongitud.value = longitud
            buttonContinuar.disabled = false
        // })
    } else {
        setMensaje.value = "Debe habilitar la geolocalización"
    }
})