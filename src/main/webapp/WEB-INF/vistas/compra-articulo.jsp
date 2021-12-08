<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Veterinaria Artemisa</title>
    <link rel="stylesheet" href="${contextPath}/css/mercadoPago.css">
</head>
<body>
<div class="container-fluid px-1 px-md-2 px-lg-4 py-5 mx-auto">
    <div class="row d-flex justify-content-center">
        <div class="col-xl-7 col-lg-8 col-md-9 col-sm-11">
            <div class="card border-0">
    <form id="form-checkout" >
        <div class="row">
            <div class="col-sm-7 border-line pb-3">
                <div class="form-group">
                    <p class="text-muted text-sm mb-0">Numero de Tarjeta</p>
                    <div class="row px-3"><input type="text" name="cardNumber" id="form-checkout__cardNumber" />
                </div>
                    <div class="form-group">
                        <p class="text-muted text-sm mb-0">Mes</p><input type="text" name="cardExpirationMonth" id="form-checkout__cardExpirationMonth" />
                    </div>
                    <div class="form-group">
                        <p class="text-muted text-sm mb-0">Año</p><input type="text" name="cardExpirationYear" id="form-checkout__cardExpirationYear" />
                    </div>
                    <div class="form-group">
                        <p class="text-muted text-sm mb-0">Nombre del titular</p><input type="text" name="cardholderName" id="form-checkout__cardholderName"/>
                    </div>
                    <div class="form-group">
                        <p class="text-muted text-sm mb-0">Email</p><input type="email" name="cardholderEmail" id="form-checkout__cardholderEmail"/>
                    </div>
                    <div class="form-group">
                        <p class="text-muted text-sm mb-0">CVV/CVC</p><input type="text" name="securityCode" id="form-checkout__securityCode" />
                    </div>  <select name="issuer" id="form-checkout__issuer"></select>


                    <div class="form-group">
                        <p class="text-muted text-sm mb-0">Documento</p><input type="text" name="identificationNumber" id="form-checkout__identificationNumber"/>
                    </div> <select name="identificationType" id="form-checkout__identificationType"></select>
          <select name="installments" id="form-checkout__installments"></select>
                    <div class="col-sm-5 text-sm-center justify-content-center pt-4 pb-4"></div><button type="submit" id="form-checkout__submit"class="btn btn-red text-center mt-4">Pagar</button>
          <progress value="0" class="progress-bar">Cargando...</progress>
    </form>
          </div>
        </div>
    </div>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://sdk.mercadopago.com/js/v2"></script>
<script>
    const mp = new MercadoPago('TEST-0bd8f6ff-620f-4df7-a047-670ca51a949d');
    // Add step #3
</script>

<script src="js/mercadoPago.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<%@ include file = "partial/footer.jsp" %>
</body>
</html>
