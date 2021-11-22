<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="notificaciones" value="${notificaciones}" scope="request" />
<jsp:include page="partial/header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <title>Veterinaria</title>
    </head>
    <body>

        <c:if test="${recompensa != null}">
            <div class="alert alert-success px-5 mx-5 mt-5" role="alert">
                <h4 class="alert-heading">${recompensa.titulo}</h4>
                <p>${recompensa.descripcion}</p>
                <hr>
                <p class="mb-0">${recompensa.caducidad}</p>
            </div>
        </c:if>

        <div class="d-flex position-fixed bottom-0 right-0 m-3" style="z-index: 5; right: 0; bottom: 0;">
            <form action="reservar-turno" method="GET">
                <button type="submit" class="btn btn-primary rounded-circle"><i class="fas fa-plus" style="font-size: 1.2rem;"></i></button>
            </form>
        </div>

        <c:if test="${empty turnos}">
            <div class="text-center mt-5 mb-5 p-5">
                <h4><span>No tenés turnos</span></h4>
                <p>Para reservar presiona en "Reservar Turno"</p>
                <br>
            </div>
        </c:if>

        <c:forEach var="turno" items="${turnos}">
            <div class="row container-fluid d-flex align-items-center mt-3">
                <div class="card-turno d-flex col-8" >
                    <img
                            src="<c:url value="/images/pelu-1.jpeg"/>"
                            class="img-fluid"
                            alt="..."
                            height="200px"
                            width="200px"
                    />
                    <div class="card-body">
                        <h5 class="card-title">Servicios: ${turno.serviciosSeleccionados}</h5>
                        <h5 class="card-title">Fecha: <fmt:formatDate value="${turno.fecha}" pattern="dd MMMM" /> ${turno.hora} hs</h5>
                        <h5 class="card-title">Estado: ${turno.estado.name()}</h5>
                    </div>
                </div>

                <div class="col-4 text-right">
                    <h3 class="card-title">$${turno.precio}</h3>
                </div>
            </div><hr>
        </c:forEach>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
        <%@ include file = "partial/footer.jsp" %>
    </body>
</html>
