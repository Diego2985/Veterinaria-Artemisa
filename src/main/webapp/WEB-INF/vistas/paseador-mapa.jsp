<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<html>
<head>
    <title>Veterinaria</title>
    <link rel="stylesheet" type="text/css" href="https://js.api.here.com/v3/3.1/mapsjs-ui.css" />
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
<script src="https://js.api.here.com/v3/3.1/mapsjs-core.js" type="text/javascript" charset="utf-8"></script>
<script src="https://js.api.here.com/v3/3.1/mapsjs-service.js" type="text/javascript" charset="utf-8"></script>
<script src="https://js.api.here.com/v3/3.1/mapsjs-ui.js" type="text/javascript" charset="utf-8"></script>
<script src="https://js.api.here.com/v3/3.1/mapsjs-mapevents.js" type="text/javascript" charset="utf-8"></script>
<script src="js/config.js"></script>
<script src="js/mapa-paseadores.js"></script>
<script>
    const paseador1 = {
        id: 1,
        estrellas: 5,
        latitud: -34.58856,
        longitud: -58.41066
    }

    const paseador2 = {
        id: 2,
        estrellas: 4.5,
        latitud: -34.585991,
        longitud: -58.407848
    }

    const paseador3 = {
        id: 3,
        estrellas: 3,
        latitud: -34.588322,
        longitud: -58.409309
    }

    const listaDePaseadores=[];
    <c:forEach items="${paseadores}" var="paseador">
    listaDePaseadores.push({
        id: "${paseador.id}",
        estrellas: "${paseador.estrellas}",
        latitud: "${paseador.latitud}",
        longitud: "${paseador.longitud}"
    })
    </c:forEach>

    mostrarPaseadores("${latitud}", "${longitud}", listaDePaseadores)
</script>
</body>
</html>
