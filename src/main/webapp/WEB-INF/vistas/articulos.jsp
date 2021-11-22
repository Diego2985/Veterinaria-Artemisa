<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>

        <div class="row container-fluid d-flex align-items-center mt-3">
            <form method="post" action="articulos">
                <div class="form-group">
                    <label for="articulo">Buscar artículo:</label>
                    <input id="articulo" type="text" name="busqueda" class="form-control mb-2" placeholder="Ingrese el titulo o descripción del artículo">
                    <button type="submit" class="btn btn-success">Buscar</button>
                </div>
            </form>
            <c:if test="${articulos.size()==0}">
                <h2>No se encontraron artículos</h2>
            </c:if>
            <c:forEach var="articulo" items="${articulos}">
                    <div class="col-6 col-sm-3 d-flex justify-content-center">
                        <div class="card text-center d-flex justify-content-center" >
                            <img
                                    src="<c:url value="/images/dogo.jpeg"/>"
                                    class="img-fluid"
                                    alt="..."
                            />
                            <div class="card-body">
                                <h5 class="card-title">${articulo.tituloArticulo}</h5>
                                <h6 class="card-text">${articulo.descripcion}</h6>
                                <div class="container text-center">
                                    <h3 class="card-title">$${articulo.precio}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
            </c:forEach>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
        <%@ include file = "partial/footer.jsp" %>
    </body>
</html>
