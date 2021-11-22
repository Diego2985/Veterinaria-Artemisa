<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Veterinaria</title>
    <!-- Font Awesome -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
            rel="stylesheet"
    />
    <!-- Google Fonts -->
    <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
    />
    <!-- MDB -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.5.0/mdb.min.css"
            rel="stylesheet"
    />
</head>

<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light" style="background-color:#f4f9f9">
    <!-- Container wrapper -->
    <div class="container-fluid">
        <!-- Toggle button -->
        <button
                class="navbar-toggler"
                type="button"
                data-mdb-toggle="collapse"
                data-mdb-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <i class="fas fa-bars"></i>
        </button>

        <!-- Collapsible wrapper -->
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <!-- Navbar brand -->
            <a class="navbar-brand mt-2 mt-lg-0" href="#">
                <img
                        src="https://image.flaticon.com/icons/png/512/489/489868.png"
                        height="45px"
                        alt=""
                        loading="lazy"
                />
            </a>
            <!-- Left links -->
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/articulos">Articulos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/listado-turnos">Mis Turnos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/paseador">Paseadores</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/calendario-vacunacion">Calendario</a>
                </li>
            </ul>
            <!-- Left links -->
        </div>
        <!-- Collapsible wrapper -->

        <!-- Right elements -->
        <div class="d-flex align-items-center">
            <!-- Icon -->
            <a class="text-reset me-3" href="#">
                <i class="fas fa-shopping-cart"></i>
            </a>

            <!-- Notifications -->
            <div class="dropstart">
                <a
                        class="text-reset me-4 dropdown"
                        href="#"
                        id="navbarDropdownMenuLink"
                        role="button"
                        data-toggle="dropdown"
                        aria-expanded="false"
                >
                    <i class="fas fa-bell"></i>
                    <c:if test="${not empty requestScope.notificaciones}">
                        <span class="badge rounded-pill badge-notification bg-danger">${requestScope.notificaciones.size()}</span>
                    </c:if>
                </a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">
                    <c:forEach var="notificacion" items="${requestScope.notificaciones}">
                        <li>
                            <form action='${notificacion.action}' method='post'>
                                <input type='hidden' value='${notificacion.id}' name='idNotificacion' />
                                <input type='submit' class="border-0 bg-light" value='${notificacion.titulo}' readonly />
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <!-- Avatar -->
            <div class="dropstart">
                <a
                        class="dropdown d-flex align-items-center"
                        href="#"
                        id="navbarDropdownMenuLink2"
                        role="button"
                        data-toggle="dropdown"
                        aria-expanded="false"
                >
                    <img
                            src="<c:url value="/images/user.png"/>"
                            class="rounded-circle"
                            height="25"
                            alt=""
                    />
                </a>
                <ul
                        class="dropdown-menu dropdown-menu-end"
                        aria-labelledby="navbarDropdownMenuLink2"
                >
                    <li>
                        <a class="dropdown-item" href="${contextPath}/mis-mascotas">Mis mascotas</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="#">Cerrar sesión</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- Right elements -->
    </div>
    <!-- Container wrapper -->
</nav>
<!-- Navbar -->
</body>
</html>
