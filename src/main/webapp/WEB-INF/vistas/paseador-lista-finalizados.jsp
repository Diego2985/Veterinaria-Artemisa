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

    </c:when>
    <c:otherwise>
      <h3>No tiene paseos finalizados</h3>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>
