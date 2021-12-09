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
        <div class="cho-container"></div>
        <script src="https://sdk.mercadopago.com/js/v2"></script>
<%--        <script>--%>
<%--            const mp = new MercadoPago('TEST-0bd8f6ff-620f-4df7-a047-670ca51a949d', {--%>
<%--                locale: 'es-AR'--%>
<%--            })--%>
<%--            const checkout = mp.checkout({--%>
<%--                preference: {--%>
<%--                    id: 'YOUR_PREFERENCE_ID'--%>
<%--                }--%>
<%--            });--%>
<%--            checkout.render({--%>
<%--                container: '.cho-container',--%>
<%--                label: 'Pagar'--%>
<%--            });--%>
<%--        </script>--%>

<%--        <!-- Placed at the end of the document so the pages load faster -->--%>
<%--        <script src="https://sdk.mercadopago.com/js/v2"></script>--%>
<%--        <script>--%>
<%--            const mp = new MercadoPago('TEST-0bd8f6ff-620f-4df7-a047-670ca51a949d');--%>
<%--            // Add step #3--%>
<%--        </script>--%>

        <script src="js/mercadoPago.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <%@ include file = "partial/footer.jsp" %>
    </body>
</html>
