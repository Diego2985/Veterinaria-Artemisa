<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: nicolasduarte
  Date: 07/06/2021
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Veterinaria</title>
    </head>
    <body>
        <div class="row d-flex">
            <a href="${pageContext.request.contextPath}/reservar-turno" class="btn btn-lg btn-primary mt-4">Reservar Turno</a>
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
                            src="<c:url value=""/>"
                            class="img-fluid"
                            alt="..."
                            height="200px"
                            width="200px"
                    />
                    <div class="card-body">
                        <h5 class="card-title">Servicios: ${turno.serviciosSeleccionados}</h5>
                        <h5 class="card-title">Fecha: <fmt:formatDate value="${turno.fecha}" pattern="dd MMMM" /> ${turno.hora} hs</h5>
                        <h5 class="card-title">Estado: ${turno.estado.name()}</h5>
                        <button class="btn" style="background-color: #a4ebf3" href="#">Ver más</button>
                    </div>
                </div>

                <div class="col-4 text-right">
                    <h3 class="card-title">$${turno.precio}</h3>
                </div>
            </div><hr>
        </c:forEach>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <%@ include file = "partial/footer.jsp" %>
    </body>
</html>
