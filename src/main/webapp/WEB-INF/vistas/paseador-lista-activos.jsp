<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="partial/header.jsp" %>
<html>
<head>
    <title>Veterinaria</title>
</head>
<body>
<div class="container mt-3">
    <h2>Paseos activos</h2>
    <c:choose>
        <c:when test="${paseos.size() > 0}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Id Paseo</th>
                    <th scope="col">Nombre de la Mascota</th>
                    <th scope="col">Domicilio</th>
                    <th scope="col">Ubicación</th>
                    <th scope="col">Tiempo restante</th>
                    <th scope="col">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="paseo" items="${paseos}">
                    <tr>
                        <td>${paseo.id}</td>
                        <td>${paseo.mascota.nombre}</td>
                        <td>${paseo.domicilio}</td>
                        <td>
                            <a href="#" data-toggle="modal" data-target="#modalImage" data-title="Ruta de Paseador a mi casa" data-image="${tiempos.get(paseo.id).imgPosicionUsuario}">
                                <img src="<c:url value="data:image/jpeg;base64,${tiempos.get(paseo.id).imgPosicionUsuario}"/>" width="100" alt="ruta">
                            </a>
                        </td>
                        <td>
                            ${tiempos.get(paseo.id).tiempoRestante} minutos
                        </td>
                        <td>
                            <button type="button" class="btn btn-success">Finalizar Paseo</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h3>No tiene paseos activos</h3>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
