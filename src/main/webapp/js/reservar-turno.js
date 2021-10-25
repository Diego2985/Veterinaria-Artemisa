
function cambiarPrecio() {
    var selectBox = document.getElementsByName("serviciosSeleccionados");
    var precio = document.getElementById("precio");

    var total = 0;

    for (var checkbox of selectBox) {
        if (checkbox.checked) {
            document.body.append(checkbox.value + ' ');
            if (checkbox.value == 1) {
                total += 500;
            } else if (checkbox.value == 2) {
                total += 400;
            } else if (checkbox.value == 3) {
                total += 300;
            }
        }
    }

    precio.innerText = "$"+total;
    console.log(total);
}