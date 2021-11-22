<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="partial/header.jsp" />
<!DOCTYPE html>
<html>
  <head>
      <title>Artemisa</title>
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

    <table class="table mx-5">
      <thead class="thead-dark">
        <tr>
          <th scope="col">Mascota</th>
          <th scope="col">Nombre</th>
          <th scope="col">Edad</th>
          <th scope="col">Vacunas</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="mascota" items="${mascotas}">
          <tr>
            <th scope="row"><img
                    src="<c:url value="/images/tipo-${mascota.tipo}.png"/>"
                    class="img-fluid"
                    alt="..."
                    height="80px"
                    width="80px"
            />
            </th>
            <td class="align-middle">${mascota.nombre}</td>
            <td class="align-middle">${mascota.edad} mes</td>
            <td class="align-middle">${mascota.id}</td>
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
