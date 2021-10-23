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
                    <input id="articulo" type="text" name="busqueda" placeholder="Ingrese el titulo o descripción del artículo">
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

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <%@ include file = "partial/footer.jsp" %>
    </body>
</html>
