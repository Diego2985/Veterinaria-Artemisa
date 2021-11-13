<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="partial/header.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <title>Veterinaria Artemisa</title>
        <link rel="stylesheet" href="${contextPath}/css/main.css">
        <link rel="stylesheet" href="${contextPath}/css/calendar.css">
        <link rel="stylesheet" href="${contextPath}/css/bootstrap.min.css">
    </head>
    <body>
        <section>
            <div id='calendar'></div>
        </section>

        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Evento</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label>Descripción:</label>
                            <label id="modalTitulo"></label>
                        </div>
                        <div class="mb-3">
                            <label>Fecha de atención:</label>
                            <label id="modalFecha"></label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <script src='${contextPath}/js/main.js' type="text/javascript"></script>
        <script src='${contextPath}/js/locales.js' type="text/javascript"></script>
        <script src="${contextPath}/js/fullcalendar.js" type="text/javascript"></script>
        <script>
            const listaDeTurnos = [];
            <c:forEach items="${vacunas}" var="vacuna">
            listaDeTurnos.push({
                title: "${vacuna.titulo}",
                start: "${vacuna.fecha}",
                color: "#ff9f89"
            })
            </c:forEach>
            <c:forEach items="${turnos}" var="turno">
            listaDeTurnos.push({
                title: "${turno.serviciosSeleccionados}",
                start: "${turno.fecha.toString()}",
                color: "#8fdf82"
            })
            </c:forEach>
            var traerEventos = function () {
                return listaDeTurnos;
            }
        </script>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

        <%@ include file="partial/footer.jsp" %>
    </body>
</html>
