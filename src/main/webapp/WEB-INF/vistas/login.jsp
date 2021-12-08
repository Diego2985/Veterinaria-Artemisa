<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Artemisa</title>
        <meta charset="ISO-8859-1">

        <!-- Bootstrap core CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href="${contextPath}/css/login.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div id="loginbox" class="col-md-4 col-sm mx-auto mt-5">
                    <%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
                    <%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
                    <%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
                    <form:form action="validar-login" method="POST" modelAttribute="datosLogin">
                        <div class="text-center mt-4">
                            <img src="https://image.flaticon.com/icons/png/512/489/489868.png"
                                 height="90px"
                                 alt=""
                                 loading="lazy"/>
                            <h3 class="form-signin-heading">Veterinaria Artemisa</h3>
                            <hr class="colorgraph"><br>
                        </div>

                        <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
                        <form:input path="email" id="email" type="email" class="form-control" placeholder="Email" />
                        <form:input path="password" type="password" id="password" class="form-control mt-2" placeholder="Password"/>

                        <button class="btn btn-primary btn-block mt-4 d-block" type="submit">Iniciar sesión</button>
                    </form:form>
                    <%--Bloque que es visible si el elemento error no esta vacio	--%>
                    <c:if test="${not empty error}">
                        <h4 class="text-danger"><span>${error}</span></h4>
                        <br>
                    </c:if>
                    ${msg}
                </div>
            </div>
        </div>
    </body>
</html>
