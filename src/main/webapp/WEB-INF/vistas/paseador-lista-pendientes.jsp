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
                        <th scope="col">Domicilio</th>
                        <th scope="col">¿Cómo llegar?</th>
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
                                <a href="#" data-toggle="modal" data-target="#modalImage" data-title="Ruta de Paseador a mi casa" data-image="${imagenes.get(paseo.id)}">
                                    <img src="<c:url value="data:image/jpeg;base64,${imagenes.get(paseo.id)}"/>" width="100" alt="ruta">
                                </a>
                            </td>
                            <td>
                                <form action="<c:url value="/paseador/comenzar-seguimiento"/>" method="post">
                                    <input type="hidden" name="idRegistro" value="${paseo.id}"/>
                                    <input type="hidden" name="idPaseador" value="${paseo.paseador.id}"/>
                                    <input type="hidden" name="idUsuario" value="${paseo.usuario.id}"/>
                                    <button type="submit" class="btn btn-success">Comenzar paseo</button>
                                </form>
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
<div class="modal fade" id="modalImage" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center"></div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
<script src="<c:url value="/js/paseos-modal-img.js" />"></script>
</body>
</html>
