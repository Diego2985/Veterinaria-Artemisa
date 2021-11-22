<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Title</title>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
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
                            <c:if test="${articulo.esNuevo}">
                                <h6><span class="badge btn-primary">¡Nuevo!</span></h6>
                                <c:if test="${not articulo.mostrado}">
                                    <div class="position-fixed bottom-0 right-0 p-3" style="z-index: 5; right: 0; bottom: 0;">
                                        <div id="toast" class="toast hide" data-autohide="false" role="alert" aria-live="assertive" aria-atomic="true">
                                            <div class="toast-header row-">
                                                <div class="col-sm">
                                                    <img src="<c:url value="/images/dogo.jpeg"/>" class="mr-2" alt="..." style="height: 40px">
                                                </div>
                                                <div class="col-sm">
                                                    <strong class="mr-4 ml-4">${articulo.descripcion}</strong>
                                                </div>
                                                <div class="col-sm">
                                                    <form action="setear-articulo-mostrado" method="POST">
                                                        <input type='hidden' value='${articulo.id}' name='idArticulo' />
                                                        <button type="submit" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                                                            <span aria-hidden="false">&times;</span>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                            <div class="toast-body">
                                                ¡Nuevo articulo para tu mascota!
                                            </div>
                                        </div>
                                    </div>
                                    <script>
                                        $('.toast').toast("show");
                                    </script>
                                </c:if>
                            </c:if>
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

        <%@ include file = "partial/footer.jsp" %>
    </body>
</html>
