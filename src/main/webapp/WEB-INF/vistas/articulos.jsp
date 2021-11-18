<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<!DOCTYPE html>
<head>
    <title>Title</title>
</head>
<html>
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

    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <figure class="card card-product-grid card-lg"> <a href="1" class="img-wrap" data-abc="true"><img src="<c:url value="/images/Raza-carne.jpg"/>"</a>
                    <figcaption class="info-wrap">
                        <div class="row">
                            <div class="col-md-8"> <a href="1" class="title" data-abc="true">Alimento - Carne</a> </div>
                            <div class="price-wrap"> <span class="price h5">$1500</span> <br> </div>
                        </div>
                    </figcaption>
                    <div class="bottom-wrap"> <a href="carrito/1" class="btn btn-primary float-right" data-abc="true"> Comprar </a>

                    </div>
                </figure>
            </div>
            <div class="col-md-4">
                <figure class="card card-product-grid card-lg"> <a href="2" class="img-wrap" data-abc="true"><img src="<c:url value="/images/Gatos-PolloLeche-.jpg"/>"</a>
                    <figcaption class="info-wrap">
                        <div class="row">
                            <div class="col-md-8"> <a href="2" class="title" data-abc="true">Alimento - Pollo</a> </div>
                            <div class="price-wrap"> <span class="price h5">$1500</span> <br> </div>
                        </div>
                    </figcaption>
                    <div class="bottom-wrap"> <a href="carrito/2" class="btn btn-primary float-right" data-abc="true"> Comprar </a>

                    </div>
                </figure>
            </div>
            <div class="col-md-4">
                <figure class="card card-product-grid card-lg"> <a href="3" class="img-wrap" data-abc="true"><img src="<c:url value="/images/Superdog.jpg"/>"</a>
                    <figcaption class="info-wrap">
                        <div class="row">
                            <div class="col-md-8"> <a href="3" class="title" data-abc="true">Ropa - Superman</a> </div>
                            <div class="price-wrap"> <span class="price h5">$1000</span> <br></div>
                        </div>
                    </figcaption>
                    <div class="bottom-wrap"> <a href="carrito/3" class="btn btn-primary float-right" data-abc="true"> Comprar </a>

                    </div>
                </figure>
            </div>
            <div class="col-md-4">
                <figure class="card card-product-grid card-lg"> <a href="4" class="img-wrap" data-abc="true"><img src="<c:url value="/images/Frontline.png"/>"</a>
                    <figcaption class="info-wrap">
                        <div class="row">
                            <div class="col-md-8"> <a href="4" class="title" data-abc="true">Pipeta - Perro</a> </div>
                            <div class="price-wrap"> <span class="price h5">$2000</span> <br></div>
                        </div>
                    </figcaption>
                    <div class="bottom-wrap"> <a href="carrito/4" class="btn btn-primary float-right" data-abc="true"> Comprar </a>

                    </div>
                </figure>

            </div>
        </div>
    </div>

            <!-- Placed at the end of the document so the pages load faster -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
            <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
            <script src="js/bootstrap.min.js" type="text/javascript"></script>
            <%@ include file = "partial/footer.jsp" %>
    </body>
</html>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                