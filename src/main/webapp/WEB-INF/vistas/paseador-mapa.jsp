<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<%@ include file = "partial/maps-scripts.jsp" %>
<html>
<head>
    <title>Veterinaria</title>
</head>
<body>
<div class="container mt-3">
    <div>
        <h2>Pasear a la mascota</h2>
        <form action="ver-paseadores" method="post">
            <div>
                <label>Por favor haga click aquí para capturar su ubicación</label>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" readonly="true" name="mensaje"
                           placeholder="Espere hasta que aparezca el mensaje de Listo" id="setMensaje"
                           aria-describedby="button-addon1">
                    <input type="hidden" name="latitud" id="setLatitud">
                    <input type="hidden" name="longitud" id="setLongitud">
                    <button class="btn btn-primary" type="button" id="getUbicacion">
                        <i class="fas fa-map-marker-alt"></i>
                    </button>
                </div>
            </div>
            <button type="submit" id="continuar" disabled="true" class="btn btn-primary">Continuar</button>
        </form>
    </div>
    <h2>Mapa de paseadores cercanos</h2>
    <p>Paseadores cerca de: <span id="locacion">${ubicacion.toString()}</span></p>
    <div class="row">
        <div class="col-6">
            <div id="mapContainer" style="width: 600px; height: 600px"></div>
        </div>
        <div class="col-6"></div>
    </div>
</div>
<script src="js/mapa-paseadores.js"></script>
<script>
    const listaDePaseadores=[];
    <c:forEach items="${paseadores}" var="paseador">
    listaDePaseadores.push({
        id: "${paseador.id}",
        estrellas: "${paseador.estrellas}",
        latitud: "${paseador.latitud}",
        longitud: "${paseador.longitud}",
        cantidadActual: "${paseador.cantidadActual}",
        cantidadMaxima: "${paseador.cantidadMaxima}"
    })
    </c:forEach>

    mostrarPaseadores("${ubicacion.coordenadas.latitud}", "${ubicacion.coordenadas.longitud}", listaDePaseadores)
</script>
<script src="js/capturar-ubicacion.js"></script>
</body>
</html>
