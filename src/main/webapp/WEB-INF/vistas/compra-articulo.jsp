<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" action="compra-articulo" modelAttribute="datosCompra">
    <div class="padding">
        <div class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-header">
                        <strong>Tarjeta de debito</strong>
                        <small>entrar los detalles de los datos</small>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="name">Nombre</label>

    <form:input path="nombre" class="form-control" type="text" placeholder="Ingresa tu nombre"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <%--@declare id="ccnumber"--%><label for="ccnumber">Numero de tarjeta de credito</label>
                                    <div class="input-group">
                                        <label>
                                            <form:input path="numeroCredito" class="form-control" type="text" placeholder="0000 0000 0000 0000" autocomplete="email"/>
                                        </label>
                                        <div class="input-group-append">
                                        <span class="input-group-text">
                                            <i class="mdi mdi-credit-card"></i>
                                        </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-4">
                                <label for="ccmonth">Mes</label>
                                <form:select path="mes" class="form-control" id="ccmonth">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                    <option>9</option>
                                    <option>10</option>
                                    <option>11</option>
                                    <option>12</option>
                                </form:select>
                            </div>
                            <div class="form-group col-sm-4">
                                <label for="ccyear">Año</label>
                                <form:select path="anio" class="form-control" id="ccyear">
                                    <option>2014</option>
                                    <option>2015</option>
                                    <option>2016</option>
                                    <option>2017</option>
                                    <option>2018</option>
                                    <option>2019</option>
                                    <option>2020</option>
                                    <option>2021</option>
                                    <option>2022</option>
                                    <option>2023</option>
                                    <option>2024</option>
                                    <option>2025</option>
                                </form:select>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group">
                                    <label for="cvv">CVV/CVC</label>
                                    <input class="form-control" id="cvv" type="text" placeholder="123">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">

                        <button class="btn btn-sm btn-success float-right" type="submit">
                            <i class="mdi mdi-gamepad-circle"></i> Continuar</button>
                        <button class="btn btn-sm btn-danger" type="reset">
                            <i class="mdi mdi-lock-reset"></i> Resetear</button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</form:form>



<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<%@ include file = "partial/footer.jsp" %>
</body>
</html>
