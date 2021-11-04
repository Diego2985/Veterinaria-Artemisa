<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="partial/header.jsp" %>
<%@ include file="partial/maps-scripts.jsp" %>
<html>
<head>
    <title>Veterinaria</title>
</head>
<body>
<div class="container mt-3">
    <h2>El paseador se encuentra en camino</h2>
    <h4>Va a llegar en aproximadamente ${distanciaYTiempo.tiempo} minutos ya que se encuentra
        a ${distanciaYTiempo.distancia} metros</h4>
    <div id="locacion"></div>
    <div id="mapContainer" style="width: 600px; height: 600px"></div>
</div>
<script src="js/paseador-llegada.js"></script>
<script>
    mostrarLlegada("${distanciaYTiempo.coordenadasUsuario.latitud}", "${distanciaYTiempo.coordenadasUsuario.longitud}")
</script>
</body>
</html>
