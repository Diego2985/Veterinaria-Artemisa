<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="partial/header.jsp" %>

<html>
<head>
    <title>Veterinaria</title>
</head>
<body>
<div class="container mt-3">
    <h2>El paseador se encuentra en camino</h2>
    <h4>Va a llegar en aproximadamente ${distanciaYTiempo.tiempo} minutos ya que se encuentra
        a ${distanciaYTiempo.distancia} metros</h4>
    <div id="mapContainer" style="width: 600px; height: 600px"></div>
    <div>
        <img alt="img" src="data:image/jpeg;base64,${imagen}"/>
    </div>
</div>
</body>
</html>
