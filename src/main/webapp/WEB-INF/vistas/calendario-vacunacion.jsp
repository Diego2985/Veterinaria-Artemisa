<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="partial/header.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <title>Veterinaria Artemisa</title>
        <link rel="stylesheet" href="${contextPath}/css/main.css">
        <link rel="stylesheet" href="${contextPath}/css/calendar.css">
    </head>
    <body>
        <section>
            <div id='calendar'></div>
        </section>

        <script src='${contextPath}/js/main.js' type="text/javascript"></script>
        <script src='${contextPath}/js/locales.js' type="text/javascript"></script>
        <script src="${contextPath}/js/calendar.js" type="text/javascript"></script>
        <script>
            const listaDeTurnos = [];
            <c:forEach items="${vacunas}" var="vacuna">
            listaDeTurnos.push({
                title: "${vacuna.titulo}",
                start: "${vacuna.fecha}"
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
