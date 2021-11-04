<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<%@ include file = "partial/maps-scripts.jsp" %>
<html>
<head>
    <title>Veterinaria</title>
</head>
<body>
<div class="container mt-3">
    <h2>Mapa de paseadores cercanos</h2>
    <p>Paseadores cerca de: <span id="locacion"></span></p>
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

    mostrarPaseadores("${latitud}", "${longitud}", listaDePaseadores)
</script>
</body>
</html>
