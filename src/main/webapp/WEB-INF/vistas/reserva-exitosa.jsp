<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Veterinaria</title>
    </head>
    <body>

        <div class="card text-center mt-5 mb-5 p-5">
            <div class="text-center">
                <img
                        class="card-img-100"
                        src="https://image.flaticon.com/icons/png/512/463/463574.png"
                        height="100px"
                        width="100px"
                        alt=""
                        loading="lazy"
                />
            </div>
            <div class="card-body">
                <h5 class="card-title">¡Reserva exitosa!</h5>
                <a href="${pageContext.request.contextPath}/listado-turnos" class="btn btn-primary mt-4">Volver</a>
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="https://unpkg.com/@popperjs/core@2"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <%@ include file = "partial/footer.jsp" %>
    </body>
</html>
