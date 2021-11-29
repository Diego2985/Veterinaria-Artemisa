<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="partial/header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>

        <div class="text-center mt-3 mb-3 p-3">
            <h4><span>Tus conversaciones</span></h4>
        </div>

        <c:if test="${empty conversaciones}">
            <div class="text-center mt-5 mb-5 p-5">
                <h4><span>No tenés conversaciones</span></h4>
                <p>Para iniciar una nueva conversación presiona en "+"</p>
                <br>
            </div>
        </c:if>

        <div class="d-flex position-fixed bottom-0 right-0 m-3" style="z-index: 5; right: 0; bottom: 0;">
            <form action="listado-usuarios" method="GET">
                <button type="submit" class="btn btn-primary btn-lg btn-floating"><i class="fas fa-plus"></i></button>
            </form>
        </div>

        <table class="table mx-5">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Foto</th>
                <th scope="col">Nombre</th>
                <th scope="col">Edad</th>
                <th scope="col">Conectar</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="conversacion" items="${conversaciones}">
                <tr>
                    <th scope="row"><img
                            src="<c:url value="/images/user.png"/>"
                            class="img-fluid"
                            alt="user"
                            height="60px"
                            width="60px"
                    />
                    </th>
                    <c:choose>
                        <c:when test="${conversacion.receptor.id != userId}">
                            <td class="align-middle">${conversacion.receptor.nombre}</td>
                            <td class="align-middle">${conversacion.receptor.edad}</td>
                            <td class="align-middle">
                                <form method="get" action="chatear">
                                    <div class="form-group">
                                        <input type="hidden" name="idUsuario" value="${conversacion.receptor.id}"/>
                                        <input type="hidden" name="idConversacion" value="${conversacion.id}"/>
                                        <button type="submit" class="btn btn-success">Conversar</button>
                                    </div>
                                </form>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td class="align-middle">${conversacion.emisor.nombre}</td>
                            <td class="align-middle">${conversacion.emisor.edad}</td>
                            <td class="align-middle">
                                <form method="get" action="chatear">
                                    <div class="form-group">
                                        <input type="hidden" name="idUsuario" value="${conversacion.emisor.id}"/>
                                        <input type="hidden" name="idConversacion" value="${conversacion.id}"/>
                                        <button type="submit" class="btn btn-success">Conversar</button>
                                    </div>
                                </form>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
    <%@ include file = "partial/footer.jsp" %>

    </body>
</html>
