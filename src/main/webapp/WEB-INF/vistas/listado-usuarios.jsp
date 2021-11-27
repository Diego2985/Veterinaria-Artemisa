<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="partial/header.jsp" />
<!DOCTYPE html>
<html>
  <head>
      <title>Title</title>
  </head>
  <body>

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
      <c:forEach var="usuario" items="${usuarios}">
        <tr>
          <th scope="row"><img
                  src="<c:url value="/images/user.png"/>"
                  class="img-fluid"
                  alt="user"
                  height="60px"
                  width="60px"
          />
          </th>
          <td class="align-middle">${usuario.nombre}</td>
          <td class="align-middle">${usuario.edad}</td>
          <td class="align-middle">
            <form method="post" action="articulos">
              <div class="form-group">
                <input type="hidden" name="idUsuario" value="${usuario.id}"/>
                <button type="submit" class="btn btn-success">Conversar</button>
              </div>
            </form>
          </td>
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
