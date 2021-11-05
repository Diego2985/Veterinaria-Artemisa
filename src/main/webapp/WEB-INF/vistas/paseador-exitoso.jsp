<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="partial/header.jsp" %>

<html>
<head>
    <title>Veterinaria</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <h2>El paseador se encuentra en camino</h2>
        <h4>Va a llegar en aproximadamente ${distanciaYTiempo.tiempo} minutos ya que se encuentra
            a ${distanciaYTiempo.distancia} metros</h4>
        <div class="col">
            <div>
                <img alt="img" src="data:image/jpeg;base64,${imagen}"/>
            </div>
            <p class="text-danger">${mensaje}</p>
        </div>
        <div class="col">
            <p>Haga click en Comenzar Seguimiento una vez que haya entregado a la mascota</p>
            <form action="comenzar-seguimiento" method="post">
                <input type="hidden" name="idRegistro" value="${registro.id}"/>
                <input type="hidden" name="idPaseador" value="${registro.paseador.id}"/>
                <input type="hidden" name="idUsuario" value="${registro.usuario.id}"/>
                <button type="submit" class="btn btn-success">Comenzar seguimiento</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
