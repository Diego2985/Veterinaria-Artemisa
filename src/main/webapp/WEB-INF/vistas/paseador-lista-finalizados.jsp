<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="partial/header.jsp" %>
<html>
<head>
  <title>Veterinaria</title>
</head>
<body>
<div class="container mt-3">
  <h2>Paseos finalizados</h2>
  <c:choose>
    <c:when test="${paseos.size() > 0}">
      <table class="table">
        <thead>
        <tr>
          <th>Mascota</th>
          <th>Hora de inicio</th>
          <th>Hora de fin</th>
          <th>Estado</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${paseos}" var="paseo">
          <tr>
            <td>${paseo.mascota.nombre}</td>
            <td>${paseo.horaInicio}</td>
            <td>${paseo.horaFinal}</td>
            <td>
              <c:choose>
                <c:when test="${paseo.estado ==2}">
                  Finalizado correctamente
                </c:when>
                <c:otherwise>
                  Cancelado
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:when>
    <c:otherwise>
      <h3>No tiene paseos finalizados</h3>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>
