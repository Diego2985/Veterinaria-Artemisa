<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="partial/header.jsp" %>
<jsp:useBean id="now" class="java.util.Date"/>

<html>
<head>
    <title>Veterinaria</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <div class="col">
            <h2>Seguimiento del paseo</h2>
            <div>
                <img alt="seguimiento" src="data:image/jpeg;base64,${imagen}"/>
            </div>
        </div>
        <div class="col">
            <c:if test="${tiempo < 10}">
                <p>Falta poco para que finalice el paseo. El mismo debería finalizar en <span class="text-danger">${tiempo} minutos.</span></p>
                <p>Cuando su mascota haya sido devuelta, haga click en Finalizar Paseo.</p>
                <form method="post" action="finalizar-paseo">
                    <input type="hidden" name="idRegistro" value="${registro.id}" />
                    <input type="hidden" name="idPaseador" value="${registro.paseador.id}" />
                    <input type="hidden" name="idUsuario" value="${registro.usuario.id}" />
                    <button class="btn btn-success" type="submit">Finalizar Paseo</button>
                </form>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
