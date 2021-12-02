<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="partial/header.jsp" %>
<html>
<head>
    <title>Veterinaria</title>
</head>
<body>
<div class="container mt-3">
    <h2>Paseos pendientes</h2>
    <c:choose>
        <c:when test="${paseos.size() > 0}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Id Paseo</th>
                        <th scope="col">Nombre de la Mascota</th>
                        <th scope="col">Dirección</th>
                        <th scope="col">¿Cómo llegar?</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="paseo" items="${paseos}">
                        <tr>
                            <td>${paseo.id}</td>
                            <td>${paseo.nombre}</td>
                            <td>${paseo.domicilio}</td>
                            <td>
                                <img src="<c:url value="data:image/jpeg;base64,${paseo.imgMapa}"/>" width="100" alt="ruta">
                            </td>
                            <td>
                                <button type="button" class="btn btn-success">Comenzar Paseo</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h3>No tiene paseos pendientes</h3>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
