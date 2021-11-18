
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<!DOCTYPE html>

<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container-publicaciones noselect">
    <div class="tarjeta-publicacion-ver-publicacion">
        <div>
           <div class="w3-row" style="display: flex;justify-content: space-around;padding: 12px 0">
                <div class="w3-col">
                    <img src="<c:url value="/images/Raza-carne.jpg"/>" style="width: 400px; height: 400px;border-radius: 12px" alt="">
                    <p><span style="font-weight: bold">Titulo:</span> ${articulo.tituloArticulo}</p>
                    <p><span style="font-weight: bold">Nombre:</span> ${articulo.descripcion}</p>
                    <p><span style="font-weight: bold">Precio:</span> ${articulo.precio}</p>
                </div>

            </div>
            <form action="metodo-de-pago" method="GET">
                <div>
                    <button class="w3-btn w3-purple" type="submit" style="width: 100%">comprar para mi</button>
                </div>

            </form>
            <a href="${pageContext.request.contextPath}/metodo-de-pago" class="btn btn-lg btn-primary mt-4">Reservar Turno</a>
        </div>
    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<%@ include file = "partial/footer.jsp" %>
</body>
</html>
